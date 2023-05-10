package com.saucedemo.bdd.hooks;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;

public class CucumberHooks {

    @After("@UseAfterHook")
    public void shutDown() {
        Selenide.closeWebDriver();
    }
}
