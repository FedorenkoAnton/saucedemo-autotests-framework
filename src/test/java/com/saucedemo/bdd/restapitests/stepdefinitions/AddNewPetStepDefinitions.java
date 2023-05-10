package com.saucedemo.bdd.restapitests.stepdefinitions;

import com.saucedemo.bdd.restapitests.responses.AddNewPetFieldsToValidate;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.RequestBuilderContainer;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.ResponseContainer;
import com.saucedemo.bdd.restapitests.utils.JsonReader;
import com.saucedemo.bdd.restapitests.utils.ResponseParser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Slf4j
public class AddNewPetStepDefinitions extends ResponseParser {
    private final RequestBuilderContainer requestBuilderContainer;
    private final ResponseContainer responseContainer;

    public AddNewPetStepDefinitions(RequestBuilderContainer requestBuilderContainer, ResponseContainer responseContainer) {
        this.requestBuilderContainer = requestBuilderContainer;
        this.responseContainer = responseContainer;
    }

    @And("assigns request body")
    public void assignRequestBody() {
        requestBuilderContainer.getRequestSpecBuilder().setBody(JsonReader.
                readJsonFromFile("src/test/resources/requestbodies/addnewpetrequestbody.json"));
    }

    @And("response body is not empty")
    public void responseBodyIsNotEmpty() {
        assertThat("Response body length is 0", getResponseBodyLength(responseContainer.getResponse()), greaterThan(0));
    }

    @When("^response is got service code should be (.*)$")
    public void responseIsGotServiceCodeShouldBeExpectedResponseCode(int statusCode) {
        int statusCodeFromResponse = responseContainer.getResponse().statusCode();
        assertThat(String.format("Status code of the response is: %s, should be '200'", statusCodeFromResponse),
                statusCodeFromResponse, is(equalTo(statusCode)));
        log.info(responseContainer.getResponse().asPrettyString());
    }
    @Then("^response body's field id should be (.*)?")
    public void responseBodySFieldIdShouldBe(int id) {
        int idFromResponse = getResponse(responseContainer.getResponse(), AddNewPetFieldsToValidate.class).getId();
        assertThat(String.format("'id' field's value is %s expected, to be %s", idFromResponse, id), idFromResponse,
                is(equalTo(id)));
    }

    @And("^response body's field name should be (.*)?")
    public void responseBodySFieldNameShouldBeSpankey(String name) {
        String nameFromResponse = getResponse(responseContainer.getResponse(), AddNewPetFieldsToValidate.class).getName();
        assertThat(String.format("'name field's value is %s, expected to be %s", nameFromResponse, name),
                nameFromResponse, is(equalTo(name)));
    }

    private Integer getResponseBodyLength(Response response) {
        return response.getBody().asString().length();
    }
}
