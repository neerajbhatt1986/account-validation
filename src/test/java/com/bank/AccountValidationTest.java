package com.bank;

import com.bank.dto.account.AccountValidationRequest;
import com.bank.dto.account.AccountValidationResponse;
import com.bank.dto.provider.ProviderDetails;
import com.bank.dto.provider.ProviderResponse;
import com.bank.service.ProviderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountValidationTest {
    @Value(value="http://localhost:${local.server.port}/validate/account")
    private String url;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    ProviderService providerService;


    @Test
    public void blank_accountNumber() throws Exception {

        AccountValidationResponse accountValidationResponse = this.restTemplate.postForObject(url, new AccountValidationRequest(), AccountValidationResponse.class);

        assertEquals(accountValidationResponse.getErrors().size(), 1);
        assertEquals(accountValidationResponse.getErrors().get(0), "Account Number is mandatory.");
    }

    @Test
    public void validAccountNumber() throws Exception {

        String accountNumber = "13456";

        AccountValidationRequest accountValidationRequest = new AccountValidationRequest();
        accountValidationRequest.setAccountNumber(accountNumber);

        Mockito.when(providerService.validateAccount(accountNumber, new ProviderDetails("provider1"))).thenReturn(new ProviderResponse(true));
        Mockito.when(providerService.validateAccount(accountNumber, new ProviderDetails("provider2"))).thenReturn(new ProviderResponse(false));
        Mockito.when(providerService.validateAccount(accountNumber, new ProviderDetails("provider3"))).thenReturn(new ProviderResponse(true));
        Mockito.when(providerService.validateAccount(accountNumber, new ProviderDetails("provider4"))).thenReturn(new ProviderResponse(false));
        AccountValidationResponse accountValidationResponse = this.restTemplate.postForObject(url, accountValidationRequest, AccountValidationResponse.class);

        assertEquals(accountValidationResponse.getResult().size(), 4);

        assertEquals(accountValidationResponse.getResult().get(0).getProvider(), "provider1");
        assertTrue(accountValidationResponse.getResult().get(0).isValid());

        assertEquals(accountValidationResponse.getResult().get(1).getProvider(), "provider2");
        assertFalse(accountValidationResponse.getResult().get(1).isValid());

        assertEquals(accountValidationResponse.getResult().get(2).getProvider(), "provider3");
        assertTrue(accountValidationResponse.getResult().get(2).isValid());

        assertEquals(accountValidationResponse.getResult().get(3).getProvider(), "provider4");
        assertFalse(accountValidationResponse.getResult().get(3).isValid());
    }


    @Test
    public void validAccountNumber_withProviders() throws Exception {

        String accountNumber = "13456";

        AccountValidationRequest accountValidationRequest = new AccountValidationRequest();
        accountValidationRequest.setAccountNumber(accountNumber);
        accountValidationRequest.setProviders(Arrays.asList("provider1", "provider3"));

        Mockito.when(providerService.validateAccount(accountNumber, new ProviderDetails("provider1"))).thenReturn(new ProviderResponse(true));
        Mockito.when(providerService.validateAccount(accountNumber, new ProviderDetails("provider3"))).thenReturn(new ProviderResponse(false));
        AccountValidationResponse accountValidationResponse = this.restTemplate.postForObject(url, accountValidationRequest, AccountValidationResponse.class);

        assertEquals(accountValidationResponse.getResult().size(), 2);

        assertEquals(accountValidationResponse.getResult().get(0).getProvider(), "provider1");
        assertTrue(accountValidationResponse.getResult().get(0).isValid());

        assertEquals(accountValidationResponse.getResult().get(1).getProvider(), "provider3");
        assertFalse(accountValidationResponse.getResult().get(1).isValid());

    }
}
