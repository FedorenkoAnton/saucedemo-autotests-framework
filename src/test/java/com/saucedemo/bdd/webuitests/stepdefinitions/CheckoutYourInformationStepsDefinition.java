package com.saucedemo.bdd.webuitests.stepdefinitions;

import com.saucedemo.bdd.webuitests.pages.CheckoutYourInformationPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.Objects;

public class CheckoutYourInformationStepsDefinition extends CheckoutYourInformationPage {
    @And("^enters (first name|last name|postal code) '([^\"]*)'$")
    public void inputValuesToFields(String fieldName, String fieldValue) {
        if (Objects.equals(fieldName, "first name")) {
            inputFirstName(fieldValue);
        } else if (Objects.equals(fieldName, "last name")) {
            inputLastName(fieldValue);
        } else if (Objects.equals(fieldName, "postal code")) {
            inputPostalCode(fieldValue);
        } else {
            throw new IllegalArgumentException(String.format("Unknown field name: %s", fieldName));
        }
    }

    @And("clicks Checkout button")
    public void clicksCheckoutButton() {
        clickOnContinueButton();
    }

    @Then("error message on Checkout-step-one page should appear and contain text {string}")
    public void errorMessageOnCheckoutStepOnePageShouldAppearAndContainTextErrorFirstNameIsRequired(
            String expectedErrorMessage) {
        checkErrorMessageIfNoCustomerDataProvided(expectedErrorMessage);
    }
}
