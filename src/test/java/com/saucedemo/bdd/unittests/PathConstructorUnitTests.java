package com.saucedemo.bdd.unittests;

import com.saucedemo.bdd.restapitests.utils.urlutils.PathConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

public class PathConstructorUnitTests {
    private Map<String, String> variableValuesForPath;

    @BeforeEach
    public void initMapVariableValues() {
        variableValuesForPath = new LinkedHashMap<>();
        variableValuesForPath.put("petId", "9999");
    }

    @Test
    void petIdPlaceholderIsReplacedWithActualValue() {
        String pathTemplate = "/pet/{{petId}}/uploadImage";
        String expectedPath = "/pet/9999/uploadImage";
        String actualPath = new PathConstructor(pathTemplate, variableValuesForPath).createPath();
        assertThat(String.format("String with replaced placeholder differs from expected: %s", actualPath), actualPath,
                Matchers.is(Matchers.equalTo(expectedPath)));
    }

    @Test
    void returnsOriginalPathIfNoVariableProvided() {
        String pathTemplate = "/pet";
        String expectedPath = "/pet";
        variableValuesForPath.clear();
        String actualPath = new PathConstructor(pathTemplate, variableValuesForPath).createPath();
        assertThat(String.format("String with replaced placeholder differs from expected: %s", actualPath), actualPath,
                Matchers.is(Matchers.equalTo(expectedPath)));
    }
}
