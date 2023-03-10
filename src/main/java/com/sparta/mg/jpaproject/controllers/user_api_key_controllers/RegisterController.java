package com.sparta.mg.jpaproject.controllers.user_api_key_controllers;

import com.sparta.mg.jpaproject.model.entities.ApiKeyTable;
import com.sparta.mg.jpaproject.services.ApiKeyService;

import com.sparta.mg.jpaproject.tools.AccessLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private final ApiKeyService apiKeyService;

    @Autowired
    public RegisterController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createApiKeyForUser(@RequestParam String userName,@RequestParam String accessLevel) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        if (!apiKeyService.userNameExists(userName)) {
            try {
                AccessLevel access = AccessLevel.valueOf(accessLevel);
                ApiKeyTable user = new ApiKeyTable();
                user.setUserName(userName);
                user.setUserLevel(access.name());
                apiKeyService.saveNewApiKeyUser(user);

                ResponseEntity<String> response = new ResponseEntity<>(
                        "{\"message\":\"A key has been generated\",\n"
                                + "\"x-api-key\":\"" + user.getApiKey() + "\"}",
                        httpHeaders,
                        HttpStatus.CREATED
                );
                return response;


            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        ResponseEntity<String> failureResponse = new ResponseEntity<>(
                "{\"message\":\"Request has failed\"}",
                httpHeaders,
                HttpStatus.BAD_REQUEST); //ToDo: Find correct status
        return failureResponse;
    }
}
