package com.saucedemo.bdd.restapitests.enums;

import com.saucedemo.bdd.webuitests.enums.OrderOptions;

import java.util.Arrays;
import java.util.Objects;

public enum HttpMethods {
    POST("Post"),
    GET("Get"),
    PUT("Put"),
    DELETE("Delete");

    public final String value;

    private HttpMethods(String value) {
        this.value = value;
    }

    public static HttpMethods from(String httpMethodValue) {
        return Arrays.stream(HttpMethods.values())
                .filter(httpMethod -> Objects.equals(httpMethod.value, httpMethodValue))
                .findFirst()
                .orElseThrow();
    }
}
