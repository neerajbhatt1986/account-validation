package com.bank.service;

import com.bank.config.ProviderConfiguration;
import com.bank.dto.account.AccountValidationRequest;
import com.bank.dto.account.AccountValidationResponse;
import com.bank.dto.account.ProviderResult;
import com.bank.dto.provider.ProviderDetails;
import com.bank.dto.provider.ProviderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class AccountValidationService {

    private final Logger logger = LoggerFactory.getLogger(AccountValidationService.class);

    ProviderConfiguration providerConfiguration;
    ProviderService providerService;
    ExecutorService executorService;
    public AccountValidationService(ProviderConfiguration providerConfiguration, ProviderService providerService, ExecutorService executorService) {
        this.providerConfiguration = providerConfiguration;
        this.providerService = providerService;
        this.executorService = executorService;
    }

    public AccountValidationResponse validateAccount(AccountValidationRequest request){

        logger.info("Validating account number {}", request.getAccountNumber());
        logger.info("providers list as input {}", request.getProviders());

        List<ProviderDetails> providers = providerConfiguration.getProviders();
        if(!CollectionUtils.isEmpty(request.getProviders()))
            providers = providers.stream().filter(providerDetails -> request.getProviders().contains(providerDetails.getName())).collect(Collectors.toList());

        List<Future<ProviderResult>> providerResponseFuture = new ArrayList<>();
        providers.forEach(providerDetails -> {
            providerResponseFuture.add(CompletableFuture.supplyAsync(() -> validateAccountOnProvider(request.getAccountNumber(), providerDetails), executorService));
        });

        AccountValidationResponse accountValidationResponse = new AccountValidationResponse();

        providerResponseFuture.forEach(providerRes -> {
            try {
                accountValidationResponse.getResult().add(providerRes.get(1100, TimeUnit.MILLISECONDS));
            } catch (Exception e) {
                logger.error("Unable to validate the account by provider");
            }
        });
        return accountValidationResponse;

    }

    private ProviderResult validateAccountOnProvider(String account, ProviderDetails providerDetails){
        ProviderResult providerResult = new ProviderResult(providerDetails.getName());
        ProviderResponse providerResponse = providerService.validateAccount(account, providerDetails);
        providerResult.setValid(providerResponse.isValid());
        return providerResult;
    }
}
