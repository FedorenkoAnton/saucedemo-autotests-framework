package com.saucedemo.bdd.restapitests.stepdefinitions.containers;

import io.restassured.builder.RequestSpecBuilder;
import lombok.Getter;
import lombok.Setter;

@Setter
public class RequestBuilderContainer {
    private RequestSpecBuilder requestSpecBuilder;

    public RequestSpecBuilder getRequestSpecBuilder() {
        if (this.requestSpecBuilder == null) {
            requestSpecBuilder = new RequestSpecBuilder();
        }
        return requestSpecBuilder;
    }
}
