package com.bank.controller;

import com.bank.dto.account.AccountValidationRequest;
import com.bank.dto.account.AccountValidationResponse;
import com.bank.service.AccountValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {

    @Autowired
    AccountValidationService validationService;
    @PostMapping("validate/account")
    public AccountValidationResponse validateAccount(@Valid @RequestBody AccountValidationRequest request){
        return validationService.validateAccount(request);
    }
}
