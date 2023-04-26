package com.saucedemo.bdd.testrunner;

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
public class RunWebUITests {
}
