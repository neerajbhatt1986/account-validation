package com.bank.dto.account;

public class ProviderResult {
    private String provider;
    private boolean isValid;

    public ProviderResult() {
    }

    public ProviderResult(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
