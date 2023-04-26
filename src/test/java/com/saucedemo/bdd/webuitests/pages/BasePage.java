package com.saucedemo.bdd.webuitests.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    protected void clickButton(String xPath) {
        $(By.xpath(xPath)).click();
    }

    protected void inputText(String xPath, String text) {
        $(By.xpath(xPath)).sendKeys(text);
    }

    protected String getTextFromWebElement(String xPath) {
        return $(By.xpath(xPath)).getText();
    }
}
