package com.bank.config;

import com.bank.dto.provider.ProviderDetails;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "config")
public class ProviderConfiguration {
    List<ProviderDetails> providers;

    public List<ProviderDetails> getProviders() {
        return providers;
    }

    public void setProviders(List<ProviderDetails> providers) {
        this.providers = providers;
    }
}
