package com.bank.service;

import com.bank.dto.provider.ProviderDetails;
import com.bank.dto.provider.ProviderResponse;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl implements ProviderService{
    @Override
    public ProviderResponse validateAccount(String account, ProviderDetails providerDetails) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new ProviderResponse();
    }
}
