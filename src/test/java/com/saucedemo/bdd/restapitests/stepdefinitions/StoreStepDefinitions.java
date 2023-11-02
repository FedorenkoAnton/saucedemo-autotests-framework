package com.saucedemo.bdd.restapitests.stepdefinitions;

import com.saucedemo.bdd.restapitests.requestbodymodel.PlaceOrderForPetRequestBody;
import com.saucedemo.bdd.restapitests.responsebodymodel.store.PlaceOrderForPetResponse;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.PetFromFindPetByStatusResponseContainer;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.ResponseContainer;
import com.saucedemo.bdd.restapitests.utils.requestbodyutils.RequestBodyUtils;
import com.saucedemo.bdd.restapitests.utils.responseparsers.ResponseParser;
import io.cucumber.java.en.And;

import java.time.Clock;
import java.time.LocalDate;

import static com.saucedemo.bdd.restapitests.constants.Paths.PATH_TO_REQUEST_BODIES_FOLDER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class StoreStepDefinitions {
    private final PetFromFindPetByStatusResponseContainer petFromFindPetByStatusResponseContainer;
    private final ResponseContainer responseContainer;


    public StoreStepDefinitions(ResponseContainer responseContainer,
                                PetFromFindPetByStatusResponseContainer petFromFindPetByStatusResponseContainer) {
        this.petFromFindPetByStatusResponseContainer = petFromFindPetByStatusResponseContainer;
        this.responseContainer = responseContainer;
    }

    @And("^sets up pet id to request body located at (.*)$")
    public void setsUpPetIdToRequestBodyLocatedAtRequestSBodyLocation(String requestBodyLocation) {
        long petId = petFromFindPetByStatusResponseContainer.getPetByStatusResponses().getId();
        PlaceOrderForPetRequestBody orderForPetRequestBody = initPlaceOrderForPetRequestBody(1, petId);
        writeRequestBodyToFile(orderForPetRequestBody, requestBodyLocation);
    }

    @And("id of ordered pet should be equal to picked pet id")
    public void idOfOrderedPetShouldBeEqualToPickedPetId() {
        long expectedPetId = petFromFindPetByStatusResponseContainer.getPetByStatusResponses().getId();
        long petIdFromResponse = getPlaceOrderForPetResponse().getPetId();

        assertThat(String.format("Expected pet id is: %s, actual is: %s", expectedPetId, petIdFromResponse),
                petIdFromResponse, is(equalTo(expectedPetId)));
    }

    @And("^status field from response body should be \\'(.*)\\'$")
    public void statusFieldFromResponseBodyShouldBePlaced(String expectedStatusFieldValue) {
        String statusFieldValueFromResponse = getPlaceOrderForPetResponse().getStatus();

        assertThat(String.format("Expected value in status field: %s, actual: %s",
                        expectedStatusFieldValue, statusFieldValueFromResponse), statusFieldValueFromResponse,
                is(equalTo(expectedStatusFieldValue)));
    }

    @And("^complete field from response body should be \\'(.*)\\'$")
    public void completeFieldFromResponseBodyShouldBeTrue(String expectedCompleteFieldValue) {
        String completeFieldValueFromResponse = String.valueOf(getPlaceOrderForPetResponse().isComplete());

        assertThat(String.format("Complete field expected value is: %s, actual: %s",
                        expectedCompleteFieldValue, completeFieldValueFromResponse),
                completeFieldValueFromResponse, is(equalTo(expectedCompleteFieldValue)));
    }

    @And("^sets up pet id as (\\d) to request body located at (.*)$")
    public void setsUpPetIdAsToRequestBodyLocatedAtJsonPlace_order_for_pet_request_bodyJson(
            int id, String requestBodyLocation) {
        PlaceOrderForPetRequestBody orderForPetRequestBody = initPlaceOrderForPetRequestBody(3, id);
        writeRequestBodyToFile(orderForPetRequestBody, requestBodyLocation);
    }

    @And("^\\'id\\' field in response body should be (\\d+)$")
    public void idFieldInResponseBodyShouldBe(long expectedPetId) {
        long actualPetId = getPlaceOrderForPetResponse().getPetId();

        assertThat(String.format("Expected pet id should be %s, but was %s", expectedPetId, actualPetId),
                actualPetId, is(equalTo(expectedPetId)));
    }

    private PlaceOrderForPetRequestBody initPlaceOrderForPetRequestBody(int orderId, long petId) {
        return new PlaceOrderForPetRequestBody(orderId,
                petId,
                1,
                LocalDate.now(Clock.systemDefaultZone()).toString(),
                "placed",
                true);
    }

    private void writeRequestBodyToFile(PlaceOrderForPetRequestBody placeOrderForPetRequestBody,
                                        String requestBodyLocation) {
        String pathToFolder = String.format(PATH_TO_REQUEST_BODIES_FOLDER, requestBodyLocation);
        RequestBodyUtils requestBodyUtils = new RequestBodyUtils();
        String requestBody = requestBodyUtils.convertObjectToJson(placeOrderForPetRequestBody);
        requestBodyUtils.writeToFile(requestBody, pathToFolder);
    }

    private PlaceOrderForPetResponse getPlaceOrderForPetResponse() {
        return new ResponseParser().getResponseAsObject(responseContainer.getResponse(),
                PlaceOrderForPetResponse.class);
    }

}
