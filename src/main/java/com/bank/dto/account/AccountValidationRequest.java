package com.bank.dto.account;


import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class AccountValidationRequest {
    @NotBlank(message = "Account Number is mandatory.")
    String accountNumber;

    List<String> providers = new ArrayList<>();

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<String> getProviders() {
        return providers;
    }

    public void setProviders(List<String> providers) {
        this.providers = providers;
    }
}
