package com.saucedemo.bdd.restapitests.stepdefinitions;

import com.saucedemo.bdd.restapitests.enums.HttpMethods;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.RequestBuilderContainer;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.ResponseContainer;
import com.saucedemo.bdd.restapitests.utils.JsonReader;
import com.saucedemo.bdd.restapitests.utils.RequestSender;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GeneralSteps {

    private final static String PET_STORE_BASE_URL = "https://petstore.swagger.io/v2";

    private final RequestBuilderContainer requestBuilderContainer;
    private final ResponseContainer responseContainer;

    public GeneralSteps(RequestBuilderContainer requestBuilderContainer, ResponseContainer responseContainer) {
        this.requestBuilderContainer = requestBuilderContainer;
        this.responseContainer = responseContainer;
    }
    @Given("^user sets up the (.*)$")
    public void userSetupTheEndpoint(String path) {
        requestBuilderContainer.getRequestSpecBuilder().setBaseUri(PET_STORE_BASE_URL)
                .setBasePath(path);
    }

    @And("^sets up request body (.*)$")
    public void userSetUpContentTypeOfRequestBodyRequestBodyContentType(String contentType) {
        requestBuilderContainer.getRequestSpecBuilder().setContentType(ContentType.fromContentType(contentType));
    }

    @And("sets up accepted (.*)$")
    public void userSetUpContentTypeOfResponseResponseBodyContentType(String mediaType) {
        requestBuilderContainer.getRequestSpecBuilder().setAccept(ContentType.fromContentType(mediaType));
    }

    @And("^sends request with (.*) method$")
    public void sendRequest(String requestMethod) {
        Response response = RequestSender.sendRequestWithMethod(requestBuilderContainer.getRequestSpecBuilder(),
                HttpMethods.from(requestMethod));
        responseContainer.setResponse(response);
    }

    @And("^assigns (.*)?")
    public void assignRequestBody(String pathToRequestBody) {
        requestBuilderContainer.getRequestSpecBuilder().setBody(JsonReader.
                readFromFile(String.format("src/test/resources/requestbodies/%s", pathToRequestBody)));
    }

}