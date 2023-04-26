package com.saucedemo.bdd.webuitests.stepdefinitions;

import com.codeborne.selenide.Configuration;
import com.saucedemo.bdd.webuitests.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginPageStepsDefinition extends LoginPage {
    @Given("user choose {string}")
    public void userIsOnLoginPage(String browserName) {
        Configuration.browser = browserName;
    }

    @And("opens Login page")
    public void opensLoginPage() {
        openLoginPage();
    }

    @And("^enters (user_name|password)(\\s*'([^\"]*)')?$")
    public void enterCredentials(String fieldName, String userName) {
        inputTextToUsernameField(fieldName, userName);
    }

    @And("clicks Login button")
    public void clicksLoginButton() {
        clickLoginButton();
    }

    @And("error message contains text {string}")
    public void errorMessageContainsTextEpicSadfaceUsernameIsRequired(String errorMessageExpectedText) {
        verifyErrorMessage(errorMessageExpectedText);
    }

    @Then("error message is displayed")
    public void errorMessageIsDisplayed() {
        checkErrorMessageBannerIsDisplayed();
    }
}
