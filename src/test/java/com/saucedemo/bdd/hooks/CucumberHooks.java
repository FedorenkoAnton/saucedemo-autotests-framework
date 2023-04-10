package com.saucedemo.bdd.hooks;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;

public class CucumberHooks {

    @After
    public void shutDown() {
        Selenide.closeWebDriver();
    }
}
