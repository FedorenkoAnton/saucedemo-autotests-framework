package com.saucedemo.bdd.stepdefinitions;

import com.saucedemo.bdd.pages.CheckoutOverviewPage;
import io.cucumber.java.en.When;

public class CheckoutOverviewStepsDefinition extends CheckoutOverviewPage{
    @When("clicks on Finish button")
    public void clicksOnFinishButton() {
        clickFinishButton();
    }
}
