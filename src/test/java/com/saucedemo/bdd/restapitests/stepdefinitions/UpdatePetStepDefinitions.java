package com.saucedemo.bdd.restapitests.stepdefinitions;

import com.saucedemo.bdd.restapitests.responsebodymodel.addpettostore.UpdatePetResponse;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.ResponseContainer;
import com.saucedemo.bdd.restapitests.utils.responseparsers.ResponseParser;
import io.cucumber.java.en.And;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UpdatePetStepDefinitions {

    private final UpdatePetResponse updatePetResponse;
    private final static String errorMessagePattern = "Pet %s from response body is %s, but expected to be %s";

    public UpdatePetStepDefinitions(ResponseContainer responseContainer) {
        this.updatePetResponse = new ResponseParser()
                .getResponseAsObject(responseContainer.getResponse(), UpdatePetResponse.class);
    }

    @And("^update response body's field 'id' should be (\\d*)$")
    public void updateResponseBodysFieldIdShouldBe(long expectedPetId) {
        long actualPetId = updatePetResponse.getId();
        assertThat(String.format(errorMessagePattern, "id", actualPetId, expectedPetId),
                actualPetId, equalTo(expectedPetId));
    }

    @And("^update response body\\'s field \\'name\\' should be (.*)$")
    public void updateResponseBodySFieldNameShouldBeDoggie(String expectedUpdatedPetName) {
        String actualPetName = updatePetResponse.getName();
        assertThat(String.format(errorMessagePattern, "name", actualPetName, expectedUpdatedPetName),
                actualPetName, equalTo(expectedUpdatedPetName));
    }
}
