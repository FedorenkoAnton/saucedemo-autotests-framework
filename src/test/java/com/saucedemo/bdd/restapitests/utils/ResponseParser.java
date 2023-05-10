package com.saucedemo.bdd.restapitests.utils;

import io.restassured.response.Response;

public class ResponseParser {

    public <T> T getResponse(Response response, String path, Class<T> targetClass) {
        return response.jsonPath().getObject(path, targetClass);
    }

    public <T> T getResponse(Response response, Class<T> targetClass) {
        return this.getResponse(response, "", targetClass);
    }
}
