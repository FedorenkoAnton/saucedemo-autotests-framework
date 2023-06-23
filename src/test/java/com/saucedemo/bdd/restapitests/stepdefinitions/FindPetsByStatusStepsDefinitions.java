package com.saucedemo.bdd.restapitests.stepdefinitions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.saucedemo.bdd.restapitests.responses.findpet.FindPetByStatusResponse;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.ResponseContainer;
import com.saucedemo.bdd.restapitests.utils.responseparsers.JsonXmlArrayToListOfPojoParser;
import io.cucumber.java.en.Then;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;

public class FindPetsByStatusStepsDefinitions {

    private final List<FindPetByStatusResponse> findPetByStatusResponses;

    public FindPetsByStatusStepsDefinitions(ResponseContainer responseContainer) {
        this.findPetByStatusResponses = new JsonXmlArrayToListOfPojoParser()
                .parseXmlOrJsonArrayToListOfPojoByMediaType(responseContainer.getResponse(), new TypeReference<>() {});
    }
    @Then("^field 'status' of each pet from response is equal to (.*)$")
    public void fieldStatusOfEachPetFromResponseIsExpectedStatus(String expectedPetStatus) {
        List<String> foundPetStatuses = getFoundPetStatuses(this.findPetByStatusResponses);
        String mismatchMessagePattern = "Expected every found pet has status: %s";
        assertThat(String.format(mismatchMessagePattern, expectedPetStatus), foundPetStatuses,
                everyItem(is(expectedPetStatus)));
    }

    private List<String> getFoundPetStatuses(List<FindPetByStatusResponse> petStatuses) {
        return petStatuses.stream()
                .map(FindPetByStatusResponse::getStatus)
                .collect(Collectors.toList());
    }
}