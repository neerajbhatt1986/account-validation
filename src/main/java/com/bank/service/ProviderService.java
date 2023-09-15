package com.bank.service;

import com.bank.config.ProviderConfiguration;
import com.bank.dto.provider.ProviderDetails;
import com.bank.dto.provider.ProviderResponse;

public interface ProviderService {

    ProviderResponse validateAccount(String account, ProviderDetails providerDetails);
}
