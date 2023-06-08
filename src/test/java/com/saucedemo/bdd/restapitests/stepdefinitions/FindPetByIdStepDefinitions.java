package com.saucedemo.bdd.restapitests.stepdefinitions;

import com.saucedemo.bdd.restapitests.responses.findpet.FindPetByIdFieldsToValidate;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.ResponseContainer;
import com.saucedemo.bdd.restapitests.utils.ResponseParser;
import io.cucumber.java.en.And;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class FindPetByIdStepDefinitions {
    private final FindPetByIdFieldsToValidate findPetByIdFieldsToValidate;

    public FindPetByIdStepDefinitions(ResponseContainer responseContainer) {
        this.findPetByIdFieldsToValidate = new ResponseParser()
                .getResponseAsObject(responseContainer.getResponse(), FindPetByIdFieldsToValidate.class);
    }
    @And("^field 'id' from response body is (.*)$")
    public void fieldIdFromResponseBodyIs(int expectedId) {
        int idFromResponse = this.findPetByIdFieldsToValidate.getId();
        assertThat(String.format("'id' field's value is %s expected, to be %s", idFromResponse, expectedId),
                idFromResponse, is(equalTo(expectedId)));
    }

    @And("^field 'name' from response body is (.*)$")
    public void fieldNameFromResponseBodyIsSpankey(String expectedName) {
        String nameFromResponse = this.findPetByIdFieldsToValidate.getName();
        assertThat(String.format("'name field's value is %s, expected to be %s", nameFromResponse, expectedName),
                nameFromResponse, is(equalTo(expectedName)));
    }
}
