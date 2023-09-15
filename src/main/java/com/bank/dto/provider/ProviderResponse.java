package com.bank.dto.provider;

public class ProviderResponse {
    boolean isValid;

    public ProviderResponse(boolean isValid) {
        this.isValid = isValid;
    }

    public ProviderResponse() {
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

}
