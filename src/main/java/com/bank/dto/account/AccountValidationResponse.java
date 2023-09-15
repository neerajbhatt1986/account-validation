package com.bank.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class AccountValidationResponse {

    List<ProviderResult> result = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<String> errors;

    public List<ProviderResult> getResult() {
        return result;
    }

    public void setResult(List<ProviderResult> result) {
        this.result = result;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}



