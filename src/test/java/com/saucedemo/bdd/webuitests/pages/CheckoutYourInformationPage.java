package com.saucedemo.bdd.webuitests.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CheckoutYourInformationPage extends BasePage {

    private final static String FIRST_NAME_FIELD_XPATH = "//input[@name='firstName']";
    private final static String LAST_NAME_FIELD_XPATH = "//input[@name='lastName']";
    private final static String POSTAL_CODE_FIELD_XPATH = "//input[@name='postalCode']";
    private final static String CONTINUE_BUTTON_XPATH = "//input[@name='continue']";
    private final static String ERROR_MESSAGE_XPATH = "//div[@class='error-message-container error']//h3";

    protected void inputFirstName(String firstName) {
        inputText(FIRST_NAME_FIELD_XPATH, firstName);
    }

    protected void inputLastName(String lastName) {
        inputText(LAST_NAME_FIELD_XPATH, lastName);
    }

    protected void inputPostalCode(String postalCode) {
        inputText(POSTAL_CODE_FIELD_XPATH, postalCode);
    }

    protected void clickOnContinueButton() {
        clickButton(CONTINUE_BUTTON_XPATH);
    }

    protected void checkErrorMessageIfNoCustomerDataProvided(String expectedErrorText) {
        assertThat("There is no banner with error message on the page", $(By.xpath(ERROR_MESSAGE_XPATH)).isDisplayed());
        assertThat("Text from banner doesn't correspond to expected text",
                getTextFromWebElement(ERROR_MESSAGE_XPATH), equalTo(expectedErrorText));
    }
}
