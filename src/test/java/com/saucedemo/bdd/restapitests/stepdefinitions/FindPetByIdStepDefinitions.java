package com.saucedemo.bdd.restapitests.stepdefinitions;

import com.saucedemo.bdd.restapitests.responses.findpet.FindPetByIdResponse;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.ResponseContainer;
import com.saucedemo.bdd.restapitests.utils.responseparsers.ResponseParser;
import io.cucumber.java.en.And;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class FindPetByIdStepDefinitions {
    private final FindPetByIdResponse findPetByIdResponse;

    public FindPetByIdStepDefinitions(ResponseContainer responseContainer) {
        this.findPetByIdResponse = new ResponseParser()
                .getResponseAsObject(responseContainer.getResponse(), FindPetByIdResponse.class);
    }
    @And("^field 'id' from response body is (.*)$")
    public void fieldIdFromResponseBodyIs(long expectedId) {
        long idFromResponse = this.findPetByIdResponse.getId();
        assertThat(String.format("'id' field's value is %s expected, to be %s", idFromResponse, expectedId),
                idFromResponse, is(equalTo(expectedId)));
    }

    @And("^field 'name' from response body is (.*)$")
    public void fieldNameFromResponseBodyIsSpankey(String expectedName) {
        String nameFromResponse = this.findPetByIdResponse.getName();
        assertThat(String.format("'name field's value is %s, expected to be %s", nameFromResponse, expectedName),
                nameFromResponse, is(equalTo(expectedName)));
    }
}
