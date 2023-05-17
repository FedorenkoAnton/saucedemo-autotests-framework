package com.saucedemo.bdd.restapitests.stepdefinitions;

import com.saucedemo.bdd.restapitests.responses.AddNewPetFieldsToValidate;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.ResponseContainer;
import com.saucedemo.bdd.restapitests.utils.ResponseParser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Slf4j
public class AddNewPetStepDefinitions {
    private final ResponseContainer responseContainer;
    private final ResponseParser responseParser;

    public AddNewPetStepDefinitions(ResponseContainer responseContainer) {
        this.responseContainer = responseContainer;
        responseParser = new ResponseParser();
    }

    @And("response body is not empty")
    public void responseBodyIsNotEmpty() {
        assertThat("Response body length is 0", getResponseBodyLength(responseContainer.getResponse()),
                greaterThan(0));
    }

    @When("^response is got service code should be (.*)$")
    public void responseIsGotServiceCodeShouldBeExpectedResponseCode(int statusCode) {
        int statusCodeFromResponse = responseContainer.getResponse().statusCode();
        assertThat(String.format("Status code of the response is: %s, should be '%s'", statusCodeFromResponse,
                        statusCode),
                statusCodeFromResponse, is(equalTo(statusCode)));
        log.info(responseContainer.getResponse().asPrettyString());
    }
    @Then("^response body's field id should be (.*)?")
    public void responseBodySFieldIdShouldBe(int id) {
        int idFromResponse = responseParser
                .getResponseAsObject(responseContainer.getResponse(), AddNewPetFieldsToValidate.class).getId();
        assertThat(String.format("'id' field's value is %s expected, to be %s", idFromResponse, id), idFromResponse,
                is(equalTo(id)));
    }

    @And("^response body's field name should be (.*)?")
    public void responseBodySFieldNameShouldBeSpankey(String name) {
        String nameFromResponse = responseParser
                .getResponseAsObject(responseContainer.getResponse(), AddNewPetFieldsToValidate.class).getName();
        assertThat(String.format("'name field's value is %s, expected to be %s", nameFromResponse, name),
                nameFromResponse, is(equalTo(name)));
    }

    private Integer getResponseBodyLength(Response response) {
        return response.getBody().asString().length();
    }
}
