package com.saucedemo.bdd.webuitests.stepdefinitions;

import com.saucedemo.bdd.webuitests.pages.CheckoutCompletePage;
import io.cucumber.java.en.Then;

import java.util.Objects;

public class CheckoutCompleteStepsDefinition extends CheckoutCompletePage {

    @Then("^Complete (header|text) is on Checkout complete page and matches the expected '([^\"]*)'$")
    public void completeTextElementIsOnCheckoutCompletePageAndMatchesTheExample(String typeOfTextElement,
                                                                                String expectedText) {
        checkCompleteMessageCorrespondsToExpected(typeOfTextElement, expectedText);
    }
}
