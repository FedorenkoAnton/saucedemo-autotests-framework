package com.saucedemo.bdd.restapitests.utils;

import com.saucedemo.bdd.restapitests.enums.HttpMethods;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class RequestSender {

    public static Response sendRequestWithMethod(RequestSpecBuilder requestSpecBuilder, HttpMethods httpMethod) {
        switch (httpMethod) {
            case GET: return RestAssured.given(requestSpecBuilder.build()).get().then().extract().response();
            case PUT: return RestAssured.given(requestSpecBuilder.build()).put().then().extract().response();
            case POST: return RestAssured.given(requestSpecBuilder.build()).post().then().extract().response();
            case DELETE: return RestAssured.given(requestSpecBuilder.build()).delete().then().extract().response();
            default:
                throw new IllegalArgumentException(String.format("Unknown type of method: %s", httpMethod.value));
        }
    }
}
