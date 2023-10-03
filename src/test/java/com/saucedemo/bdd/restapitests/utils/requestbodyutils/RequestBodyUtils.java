package com.saucedemo.bdd.restapitests.utils.requestbodyutils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestBodyUtils extends BasicRequestBodyUtils {
    public <T> String convertObjectToJson(T object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String objectAsJson;
        try {
            objectAsJson = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }

        return objectAsJson;
    }

    public void writeToFile(String jsonContent, String pathToFile) {
        super.writeToFile(jsonContent, pathToFile);
    }
}
