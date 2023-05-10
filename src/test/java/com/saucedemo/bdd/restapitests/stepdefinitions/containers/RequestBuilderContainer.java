package com.saucedemo.bdd.restapitests.stepdefinitions.containers;

import io.restassured.builder.RequestSpecBuilder;
import lombok.Getter;

@Getter
public class RequestBuilderContainer {
    private final RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
}
