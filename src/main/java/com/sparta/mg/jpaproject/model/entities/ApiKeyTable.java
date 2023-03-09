package com.sparta.mg.jpaproject.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "api_key_table")
public class ApiKeyTable {

    @Id
    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @Column(name = "api_key", nullable = false, unique = true)
    private String apiKey;

    @Size(max = 4)
    @NotNull
    @Column(name = "user_level", nullable = false, length = 4)
    private String userLevel;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public String toString() {
        return "ApiKeyTable{" +
                "userName='" + userName + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", userLevel='" + userLevel + '\'' +
                '}';
    }
}
