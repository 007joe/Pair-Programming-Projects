package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class AccountService {
    //class presets
    private String API_BASE_URL = "http://localhost:8080/";

    private RestTemplate restTemplate = new RestTemplate();

    //getBalance method that uses authenticated token to receive unique balance of current user
    public BigDecimal getBalance(String token) {
   BigDecimal balance = new BigDecimal(0);
   try {
       ResponseEntity<BigDecimal> entity = restTemplate.exchange(API_BASE_URL + "accounts",
               HttpMethod.GET, makeAuthEntity(token), BigDecimal.class);
       balance = entity.getBody();
   } catch (RestClientResponseException | ResourceAccessException e) {
       BasicLogger.log(e.getMessage());
   }
   return balance;
    }
    //preSetting authentication for client
    private HttpEntity makeAuthEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }
}
