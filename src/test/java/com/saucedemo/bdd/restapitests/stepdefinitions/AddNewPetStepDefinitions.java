package com.saucedemo.bdd.restapitests.stepdefinitions;

import com.saucedemo.bdd.restapitests.responses.AddNewPetFieldsToValidate;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.ResponseContainer;
import com.saucedemo.bdd.restapitests.utils.ResponseParser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@Slf4j
public class AddNewPetStepDefinitions {
    private final ResponseContainer responseContainer;
    private final ResponseParser responseParser;
    private AddNewPetFieldsToValidate addNewPetFieldsToValidate;

    public AddNewPetStepDefinitions(ResponseContainer responseContainer) {
        this.responseContainer = responseContainer;
        responseParser = new ResponseParser();
    }


    @Then("^response body's field id should be (.*)?")
    public void responseBodySFieldIdShouldBe(int id) {
        this.addNewPetFieldsToValidate = responseParser
                .getResponseAsObject(responseContainer.getResponse(), AddNewPetFieldsToValidate.class);
        int idFromResponse = this.addNewPetFieldsToValidate.getId();
        assertThat(String.format("'id' field's value is %s expected, to be %s", idFromResponse, id), idFromResponse,
                is(equalTo(id)));
    }

    @And("^response body's field name should be (.*)?")
    public void responseBodySFieldNameShouldBeSpankey(String name) {
        String nameFromResponse = this.addNewPetFieldsToValidate.getName();
        assertThat(String.format("'name field's value is %s, expected to be %s", nameFromResponse, name),
                nameFromResponse, is(equalTo(name)));
    }
}
