package com.saucedemo.bdd.webuitests.pages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LoginPage extends BasePage {

    private final static String BASE_URL = "https://www.saucedemo.com/";

    private final static String USERNAME_INPUT_XPATH = "//input[@name='user-name']";
    private final static String PASSWORD_INPUT_XPATH = "//input[@name='password']";
    private final static String LOGIN_BUTTON_XPATH = "//input[@class='submit-button btn_action']";
    private final static String ERROR_MESSAGE_BANNER_XPATH = "//h3[@data-test='error']";

    protected void openLoginPage() {
        Selenide.open(BASE_URL);
    }

    protected void inputTextToUsernameField(String inputFieldName, String inputValue) {
        if (Objects.equals(inputFieldName, "user_name")) {
            inputText(USERNAME_INPUT_XPATH, inputValue);
        } else if (Objects.equals(inputFieldName, "password")) {
            inputText(PASSWORD_INPUT_XPATH, choosePassword(inputValue));
        } else {
            throw new IllegalArgumentException(String.format("Unknown name of field: %s", inputFieldName));
        }
    }

    protected void inputTextToPasswordField(String password) {
        inputText(PASSWORD_INPUT_XPATH, password);
    }

    protected void clickLoginButton() {
        clickButton(LOGIN_BUTTON_XPATH);
    }

    protected void verifyErrorMessage(String expectedErrorMessage) {
        assertThat("Error message text doesn't correspond to expected",
                getTextFromWebElement(ERROR_MESSAGE_BANNER_XPATH), equalTo(expectedErrorMessage));
    }

    protected void checkErrorMessageBannerIsDisplayed() {
        assertThat("Error message is not displayed", $(By.xpath(ERROR_MESSAGE_BANNER_XPATH)).isDisplayed());
    }

    private String choosePassword(String inputValue) {
        if (inputValue == null) {
            return System.getenv("saucedemoPassword");
        }

        return inputValue;
    }
}
