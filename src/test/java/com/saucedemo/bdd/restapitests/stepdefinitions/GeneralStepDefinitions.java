package com.saucedemo.bdd.restapitests.stepdefinitions;

import com.saucedemo.bdd.restapitests.enums.HttpMethods;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.RequestBuilderContainer;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.ResponseContainer;
import com.saucedemo.bdd.restapitests.utils.RequestSender;
import com.saucedemo.bdd.restapitests.utils.SetBodyUtils;
import com.saucedemo.bdd.restapitests.utils.urlutils.PathConstructor;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
@Slf4j
public class GeneralStepDefinitions {

    private final static String PET_STORE_BASE_URL = "https://petstore.swagger.io/v2";

    private final RequestBuilderContainer requestBuilderContainer;
    private final ResponseContainer responseContainer;

    private ContentType requestBodyContentType;
    private final LinkedHashMap<String, String> variablePartsOfPath = new LinkedHashMap<>();

    public GeneralStepDefinitions(RequestBuilderContainer requestBuilderContainer, ResponseContainer responseContainer) {
        this.requestBuilderContainer = requestBuilderContainer;
        this.responseContainer = responseContainer;
    }

    @Given("^user sets up the endpoint (.*)$")
    public void userSetupTheEndpoint(String path) {
        requestBuilderContainer.getRequestSpecBuilder().setBaseUri(PET_STORE_BASE_URL)
                .setBasePath(new PathConstructor(path, variablePartsOfPath).createPath());
    }

    @And("^sets up request body (.*)$")
    public void userSetUpContentTypeOfRequestBodyRequestBodyContentType(String contentType) {
        requestBodyContentType = ContentType.fromContentType(contentType);
        requestBuilderContainer.getRequestSpecBuilder().setContentType(requestBodyContentType);
    }

    @And("sets up accepted media type (.*)$")
    public void userSetUpContentTypeOfResponseResponseBodyContentType(String mediaType) {
        requestBuilderContainer.getRequestSpecBuilder().setAccept(ContentType.fromContentType(mediaType));
    }

    @And("^assigns request body located at (.*)?")
    public void assignRequestBody(String pathToRequestBody) {
        String pathToFilePattern = String.format("src/test/resources/requestbodies/%s", pathToRequestBody);
        SetBodyUtils.setBodyToRequestByContentType(pathToFilePattern, requestBodyContentType, requestBuilderContainer);
    }

    @And("^sends request to \\'(.*)\\' with http method (.*)$")
    public void sendRequest(String endpointName, String requestMethod) {
        Response response = RequestSender.sendRequestWithMethod(requestBuilderContainer.getRequestSpecBuilder(),
                HttpMethods.from(requestMethod));
        responseContainer.setResponse(response);
        log.info(logResponse(endpointName, responseContainer));
    }

    @Given("^user set up pet id (.*)?")
    public void userSetUpPetId(String petId) {
        this.variablePartsOfPath.put("petId", petId);
    }

    private String logResponse(String endpointName, ResponseContainer responseContainer) {
        String response = responseContainer.getResponse().asPrettyString();
        String newLineAndMultipleAstrix = "\n*******************************************";
        return String.format("\nResponse from %s: %s%s", endpointName, response, newLineAndMultipleAstrix);
    }
}