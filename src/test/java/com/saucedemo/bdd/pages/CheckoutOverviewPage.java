package com.saucedemo.bdd.pages;

public class CheckoutOverviewPage extends BasePage {

    private final static String FINISH_BUTTON_XPATH = "//button[@name='finish']";

    protected void clickFinishButton() {
        clickButton(FINISH_BUTTON_XPATH);
    }
}
