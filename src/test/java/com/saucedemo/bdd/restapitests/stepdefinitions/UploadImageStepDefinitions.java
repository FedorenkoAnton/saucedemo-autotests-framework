package com.saucedemo.bdd.restapitests.stepdefinitions;

import com.saucedemo.bdd.restapitests.responses.addpettostore.UploadImageFieldsToValidate;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.ResponseContainer;
import com.saucedemo.bdd.restapitests.utils.ResponseParser;
import io.cucumber.java.en.And;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UploadImageStepDefinitions {
    private final ResponseContainer responseContainer;
    private final ResponseParser responseParser;
    private UploadImageFieldsToValidate uploadImageFieldsToValidate;

    public UploadImageStepDefinitions(ResponseContainer responseContainer) {
        this.responseContainer = responseContainer;
        responseParser = new ResponseParser();
    }
    @And("^status code from response body is (.*)?")
    public void checkStatusCodeInResponseBody(int expectedStatusCode) {
        uploadImageFieldsToValidate = responseParser.getResponseAsObject(responseContainer.getResponse(),
                UploadImageFieldsToValidate.class);
        int actualStatusCode = uploadImageFieldsToValidate.getCode();
        assertThat(String.format("Expected status code is %s, but actual is %s", expectedStatusCode, actualStatusCode),
                actualStatusCode, equalTo(expectedStatusCode));
    }

    @And("^file size is (.*) bytes?")
    public void fileSizeIsBytes(long expectedFileSize) {
        long actualFileSize = getFileSize(uploadImageFieldsToValidate.getMessage());
        assertThat(String.format("Expected file size is %s, but actual is %s", expectedFileSize, actualFileSize),
                actualFileSize, equalTo(expectedFileSize));
    }

    private long getFileSize(String messageField) {
        String[] messageFieldDividedByComma = messageField.split(",");
        String fileSize = messageFieldDividedByComma[1].replaceAll("\\D*", "");

        return Long.parseLong(fileSize);
    }
}
