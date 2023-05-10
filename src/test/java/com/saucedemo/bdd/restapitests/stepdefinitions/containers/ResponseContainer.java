package com.saucedemo.bdd.restapitests.stepdefinitions.containers;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseContainer {
    private Response response;
}
