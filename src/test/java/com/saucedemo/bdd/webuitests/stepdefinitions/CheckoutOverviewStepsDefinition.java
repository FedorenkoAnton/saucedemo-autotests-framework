package com.saucedemo.bdd.webuitests.stepdefinitions;

import com.saucedemo.bdd.webuitests.pages.CheckoutOverviewPage;
import io.cucumber.java.en.When;

public class CheckoutOverviewStepsDefinition extends CheckoutOverviewPage{
    @When("clicks on Finish button")
    public void clicksOnFinishButton() {
        clickFinishButton();
    }
}
