package com.sparta.mg.jpaproject.services;

import com.sparta.mg.jpaproject.model.entities.ApiKeyTable;
import com.sparta.mg.jpaproject.model.repositories.ApiKeyTableRepository;
import com.sparta.mg.jpaproject.tools.AccessLevel;
import com.sparta.mg.jpaproject.tools.CRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApiKeyService {

    private final ApiKeyTableRepository apiKeyTableRepository;

    @Autowired
    public ApiKeyService(ApiKeyTableRepository apiKeyTableRepository) {
        this.apiKeyTableRepository = apiKeyTableRepository;
    }

    private String generateKey() {
        return UUID.randomUUID().toString();
    }

    public ApiKeyTable saveNewApiKeyUser(ApiKeyTable userApiTable) {
        userApiTable.setApiKey(generateKey());
        apiKeyTableRepository.save(userApiTable);
        return userApiTable;
    }

    public boolean userNameExists(String username) {
        return apiKeyTableRepository.existsById(username);
    }

    public boolean validateUser(String apiKey, CRUD crud) {
        Optional<ApiKeyTable> optionalUser = apiKeyTableRepository.getApiUserFromApiKey(apiKey);
        System.out.println(optionalUser);

        if (optionalUser.isPresent()) {
            ApiKeyTable user = optionalUser.get();
            if (Objects.equals(user.getUserLevel(), AccessLevel.ADMIN.name())) {
                return true;
            } else if (Objects.equals(user.getUserLevel(), AccessLevel.UPDATE.name())) {
                if (crud == CRUD.DELETE) {
                    return false;
                } else {
                    return true;
                }
            } else {
                if (crud == CRUD.READ) {
                    return true;
                }
                else {
                    return false;
                }
            }

        } else {
            return false;
        }
    }

    public ResponseEntity<String> getInvalidApiKeyResponse() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        ResponseEntity<String> invalidApiKeyResponse = new ResponseEntity<>(
                "{\"message\":\"The api key is invalid for such a request\"}",
                httpHeaders,
                HttpStatus.BAD_GATEWAY //ToDo: Find correct status
        );
        return invalidApiKeyResponse;
    }
}
