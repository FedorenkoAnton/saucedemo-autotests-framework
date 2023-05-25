package com.saucedemo.bdd.restapitests.stepdefinitions;

import com.saucedemo.bdd.restapitests.stepdefinitions.containers.ResponseContainer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Slf4j
public class GeneralChecks {
    private final ResponseContainer responseContainer;

    public GeneralChecks(ResponseContainer responseContainer) {
        this.responseContainer = responseContainer;
    }
    @And("response body is not empty")
    public void responseBodyIsNotEmpty() {
        assertThat("Response body length is 0", getResponseBodyLength(responseContainer.getResponse()),
                greaterThan(0));
    }

    @When("^response is got service code should be (.*)$")
    public void responseIsGotServiceCodeShouldBeExpectedResponseCode(int statusCode) {
        log.info(responseContainer.getResponse().asPrettyString());
        int statusCodeFromResponse = responseContainer.getResponse().statusCode();
        assertThat(String.format("Status code of the response is: %s, should be '%s'", statusCodeFromResponse,
                        statusCode),
                statusCodeFromResponse, is(equalTo(statusCode)));
    }

    private Integer getResponseBodyLength(Response response) {
        return response.getBody().asString().length();
    }
}
