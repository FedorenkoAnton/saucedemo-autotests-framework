package com.saucedemo.bdd.stepdefinitions;

import com.saucedemo.bdd.pages.CheckoutCompletePage;
import io.cucumber.java.en.Then;

import java.util.Objects;

public class CheckoutCompleteStepsDefinition extends CheckoutCompletePage {

    @Then("^Complete (header|text) is on Checkout complete page and matches the expected '([^\"]*)'$")
    public void completeTextElementIsOnCheckoutCompletePageAndMatchesTheExample(String completeTextElement,
                                                                                String expectedHeaderText) {
        if (Objects.equals(completeTextElement, "header")) {
            checkCompleteHeaderDisplayed();
            checkCompleteHeaderTextCorrespondsToExpected(expectedHeaderText);
        } else if (Objects.equals(completeTextElement, "text")) {
            checkCompleteTextDisplayed();
            checkCompleteTextCorrespondsToExpected(expectedHeaderText);
        }
    }
}
