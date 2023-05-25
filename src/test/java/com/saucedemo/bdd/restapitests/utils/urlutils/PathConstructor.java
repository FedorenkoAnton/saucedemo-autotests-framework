package com.saucedemo.bdd.restapitests.utils.urlutils;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

public class PathConstructor {
    private final String path;
    private final Map<String, String> variablePartOfPath;

    public PathConstructor(String path, Map<String, String> variablePartOfPath) {
        this.path = path;
        this.variablePartOfPath = variablePartOfPath;
    }

    public String createPath() {
        if (!variablePartOfPath.isEmpty()) {
            MustacheFactory mustacheFactory = new DefaultMustacheFactory();
            Mustache mustache = mustacheFactory.compile(new StringReader(path), "path");
            StringWriter stringWriter = new StringWriter();
            mustache.execute(stringWriter, variablePartOfPath);

            return stringWriter.toString();
        }

        return path;
    }

}
