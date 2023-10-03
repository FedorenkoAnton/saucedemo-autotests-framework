package com.saucedemo.bdd.restapitests.stepdefinitions;

import com.saucedemo.bdd.restapitests.enums.HttpMethods;
import com.saucedemo.bdd.restapitests.enums.PetStatus;
import com.saucedemo.bdd.restapitests.requestbodymodel.PlaceOrderForPetRequestBody;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.PetFromFindPetByStatusResponseContainer;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.RequestBuilderContainer;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.ResponseContainer;
import com.saucedemo.bdd.restapitests.utils.RequestSender;
import com.saucedemo.bdd.restapitests.utils.requestbodyutils.RequestBodyUtils;
import com.saucedemo.bdd.restapitests.utils.requestbodyutils.SetBodyUtils;
import com.saucedemo.bdd.restapitests.utils.urlutils.PathConstructor;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.time.LocalDate;
import java.util.LinkedHashMap;

@Slf4j
public class GeneralStepDefinitions {

    private final static String PET_STORE_BASE_URL = "https://petstore.swagger.io/v2";
    private final static String PATH_TO_REQUEST_BODIES_FOLDER = "src/test/resources/requestbodies/%s";

    private final RequestBuilderContainer requestBuilderContainer;
    private final ResponseContainer responseContainer;
    private final PetFromFindPetByStatusResponseContainer petFromFindPetByStatusResponseContainer;

    private ContentType requestBodyContentType;
    private final LinkedHashMap<String, String> variablePartsOfPath = new LinkedHashMap<>();

    public GeneralStepDefinitions(RequestBuilderContainer requestBuilderContainer,
                                  ResponseContainer responseContainer,
                                  PetFromFindPetByStatusResponseContainer petFromFindPetByStatusResponseContainer) {
        this.requestBuilderContainer = requestBuilderContainer;
        this.responseContainer = responseContainer;
        this.petFromFindPetByStatusResponseContainer = petFromFindPetByStatusResponseContainer;
    }

    @Given("^user sets up the endpoint (.*)$")
    public void userSetupTheEndpoint(String path) {
        String endpoint = new PathConstructor(path, variablePartsOfPath).createPath();
        requestBuilderContainer.setRequestSpecBuilder(new RequestSpecBuilder());
        requestBuilderContainer.getRequestSpecBuilder().setBaseUri(PET_STORE_BASE_URL)
                .setBasePath(endpoint);
        log.info(String.format("Send request to endpoint: %s", PET_STORE_BASE_URL + endpoint));
    }

    @And("^sets up request body (.*)$")
    public void userSetUpContentTypeOfRequestBodyRequestBodyContentType(String contentType) {
        requestBodyContentType = ContentType.fromContentType(contentType);
        requestBuilderContainer.getRequestSpecBuilder().setContentType(requestBodyContentType);
    }

    @And("sets up accepted media type (.*)$")
    public void userSetUpContentTypeOfResponseResponseBodyContentType(String mediaType) {
        requestBuilderContainer.getRequestSpecBuilder().setAccept(ContentType.fromContentType(mediaType));
    }

    @And("^assigns request body located at (.*)?")
    public void assignRequestBody(String pathToRequestBody) {
        String pathToFilePattern = String.format(PATH_TO_REQUEST_BODIES_FOLDER, pathToRequestBody);
        new SetBodyUtils().setBodyToRequestByContentType(pathToFilePattern, requestBodyContentType, requestBuilderContainer);
    }

    @And("^sends request to \\'(.*)\\' with http method (.*)$")
    public void sendRequest(String endpointName, String requestMethod) {
        Response response = RequestSender.sendRequestWithMethod(
                requestBuilderContainer.getRequestSpecBuilder().log(LogDetail.ALL), HttpMethods.from(requestMethod));
        responseContainer.setResponse(response);
        log.info(logResponse(endpointName, responseContainer));
    }

    @Given("^user set up pet id (.*)$")
    public void userSetUpPetId(String petId) {
        this.variablePartsOfPath.put("petId", petId);
    }

    @And("^user sets up pet (.*) status to the request$")
    public void userSetsUpPetStatusToTheRequest(String petStatus) {
        requestBuilderContainer.getRequestSpecBuilder().addParam("status", PetStatus.from(petStatus).value);
    }

    @And("^sets up pet id to request body located at (.*)$")
    public void setsUpPetIdToRequestBodyLocatedAtRequestSBodyLocation(String requestBodyLocation) {
        long petId = petFromFindPetByStatusResponseContainer.getPetByStatusResponses().getId();
        String pathToFolder = String.format(PATH_TO_REQUEST_BODIES_FOLDER, requestBodyLocation);
        RequestBodyUtils requestBodyUtils = new RequestBodyUtils();
        PlaceOrderForPetRequestBody orderForPetRequestBody =
                new PlaceOrderForPetRequestBody(1,
                        petId,
                        1,
                        LocalDate.now(Clock.systemDefaultZone()).toString(),
                        "placed",
                        true);
        String requestBody = requestBodyUtils.convertObjectToJson(orderForPetRequestBody);
        System.out.println("****************************" + requestBody + "*************************");
        requestBodyUtils.writeToFile(requestBody, pathToFolder);


    }

    private String logResponse(String endpointName, ResponseContainer responseContainer) {
        String response = responseContainer.getResponse().asPrettyString();
        String newLineAndMultipleAstrix = "\n*******************************************";
        return String.format("\nResponse from %s: %s%s", endpointName, response, newLineAndMultipleAstrix);
    }
}