package com.saucedemo.bdd.webuitests.pages;

import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CheckoutCompletePage {

    private final static String COMPLETE_HEADER_XPATH = "//h2[@class='complete-header']";
    private final static String COMPLETE_TEXT_XPATH = "//div[@class='complete-text']";

    protected void checkCompleteHeaderDisplayed() {
        assertThat("Complete header is not displayed", $(By.xpath(COMPLETE_HEADER_XPATH)).isDisplayed());
    }

    private void checkCompleteHeaderTextCorrespondsToExpected(String expectedHeaderText) {
        String completeHeaderText = $(By.xpath(COMPLETE_HEADER_XPATH)).getText();
        String mismatchPattern = "Complete header text doesn't correspond to expected: %s";
        assertThat(String.format(mismatchPattern, completeHeaderText), completeHeaderText, equalTo(expectedHeaderText));
    }

    protected void checkCompleteTextDisplayed() {
        assertThat("Complete text is not displayed", $(By.xpath(COMPLETE_TEXT_XPATH)).isDisplayed());
    }

    private void checkCompleteTextCorrespondsToExpected(String expectedCompleteText) {
        String completeText = $(By.xpath(COMPLETE_TEXT_XPATH)).getText();
        String mismatchPattern = "Complete text doesn't correspond to expected: %s";
        assertThat(String.format(mismatchPattern, completeText), completeText, equalTo(expectedCompleteText));

    }

    protected void checkCompleteMessageCorrespondsToExpected(String typeOfTextElement, String expectedText) {
        if (Objects.equals(typeOfTextElement, "header")) {
            checkCompleteHeaderDisplayed();
            checkCompleteHeaderTextCorrespondsToExpected(expectedText);
        } else if (Objects.equals(typeOfTextElement, "text")) {
            checkCompleteTextDisplayed();
            checkCompleteTextCorrespondsToExpected(expectedText);
        }
    }
}
