package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

public class TransferService {
    //auto base URL
    private String API_BASE_URL = "http://localhost:8080/";
    //creates new rest template
    private RestTemplate restTemplate = new RestTemplate();

    public List<User> listAllUsers(String token) {
        User users = null;
        try {
            ResponseEntity<User> entity = restTemplate.exchange(API_BASE_URL + "users",
                    HttpMethod.GET, makeAuthEntity(token), User.class);
            users = entity.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return listAllUsers(token);

    }

    //preSetting authentication for client
    private HttpEntity makeAuthEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;

    }
}
