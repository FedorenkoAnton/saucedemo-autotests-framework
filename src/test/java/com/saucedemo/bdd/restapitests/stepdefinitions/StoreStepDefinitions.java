package com.saucedemo.bdd.restapitests.stepdefinitions;

import com.saucedemo.bdd.restapitests.requestbodymodel.PlaceOrderForPetRequestBody;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.PetFromFindPetByStatusResponseContainer;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.ResponseContainer;
import com.saucedemo.bdd.restapitests.utils.responseparsers.ResponseParser;
import io.cucumber.java.en.And;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class StoreStepDefinitions {
    private final PetFromFindPetByStatusResponseContainer petFromFindPetByStatusResponseContainer;
    private final PlaceOrderForPetRequestBody placeOrderForPetRequestBody;

    public StoreStepDefinitions(ResponseContainer responseContainer,
                                PetFromFindPetByStatusResponseContainer petFromFindPetByStatusResponseContainer) {
        this.petFromFindPetByStatusResponseContainer = petFromFindPetByStatusResponseContainer;
        this.placeOrderForPetRequestBody = new ResponseParser().getResponseAsObject(responseContainer.getResponse(),
                PlaceOrderForPetRequestBody.class);
    }

    @And("id of ordered pet should be equal to picked pet id")
    public void idOfOrderedPetShouldBeEqualToPickedPetId() {
        long expectedPetId = petFromFindPetByStatusResponseContainer.getPetByStatusResponses().getId();
        long petIdFromResponse = placeOrderForPetRequestBody.getPetId();

        assertThat(String.format("Expected pet id is: %s, actual is: %s", expectedPetId, petIdFromResponse),
                petIdFromResponse, is(equalTo(expectedPetId)));
    }

    @And("^status field from response body should be \\'(.*)\\'$")
    public void statusFieldFromResponseBodyShouldBePlaced(String expectedStatusFieldValue) {
        String statusFieldValueFromResponse = placeOrderForPetRequestBody.getStatus();

        assertThat(String.format("Expected value in status field: %s, actual: %s",
                        expectedStatusFieldValue, statusFieldValueFromResponse), statusFieldValueFromResponse,
                is(equalTo(expectedStatusFieldValue)));
    }

    @And("^complete field from response body should be \\'(.*)\\'$")
    public void completeFieldFromResponseBodyShouldBeTrue(String expectedCompleteFieldValue) {
        String completeFieldValueFromResponse = String.valueOf(placeOrderForPetRequestBody.isComplete());

        assertThat(String.format("Complete field expected value is: %s, actual: %s",
                        expectedCompleteFieldValue, completeFieldValueFromResponse),
                completeFieldValueFromResponse, is(equalTo(expectedCompleteFieldValue)));
    }
}
