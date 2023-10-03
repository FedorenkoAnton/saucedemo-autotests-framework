package com.saucedemo.bdd.restapitests.stepdefinitions;

import com.saucedemo.bdd.restapitests.responsebodymodel.addpettostore.UploadImageResponse;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.ResponseContainer;
import com.saucedemo.bdd.restapitests.utils.responseparsers.ResponseParser;
import io.cucumber.java.en.And;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UploadImageStepDefinitions {
    private final ResponseContainer responseContainer;
    private final ResponseParser responseParser;
    private UploadImageResponse uploadImageResponse;

    public UploadImageStepDefinitions(ResponseContainer responseContainer) {
        this.responseContainer = responseContainer;
        responseParser = new ResponseParser();
    }

    @And("^status code from response body is (.*)?")
    public void checkStatusCodeInResponseBody(int expectedStatusCode) {
        uploadImageResponse = responseParser.getResponseAsObject(responseContainer.getResponse(),
                UploadImageResponse.class);
        int actualStatusCode = uploadImageResponse.getCode();
        assertThat(String.format("Expected status code is %s, but actual is %s", expectedStatusCode, actualStatusCode),
                actualStatusCode, equalTo(expectedStatusCode));
    }

    @And("^file size is (.*) bytes?")
    public void fileSizeIsBytes(long expectedFileSize) {
        long actualFileSize = getFileSize(uploadImageResponse.getMessage());
        assertThat(String.format("Expected file size is %s, but actual is %s", expectedFileSize, actualFileSize),
                actualFileSize, equalTo(expectedFileSize));
    }

    private long getFileSize(String messageField) {
        String[] messageFieldDividedByComma = messageField.split(",");
        String fileSize = messageFieldDividedByComma[1].replaceAll("\\D*", "");

        return Long.parseLong(fileSize);
    }
}
