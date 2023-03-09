package com.sparta.mg.jpaproject.model.repositories;

import com.sparta.mg.jpaproject.model.entities.ApiKeyTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ApiKeyTableRepository extends JpaRepository<ApiKeyTable, String> {

    @Query(value = "SELECT a FROM ApiKeyTable a WHERE a.apiKey = :apiKey")
    Optional<ApiKeyTable> getApiUserFromApiKey(String apiKey);
}