package com.saucedemo.bdd.restapitests.utils.requestbodyutils;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

import java.io.*;

public class BasicRequestBodyUtils {
    protected String readStringFromFile(String path) {
        File file = new File(path);
        CharSource charSource = Files.asCharSource(file, Charsets.UTF_8);

        try {
            return charSource.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void writeToFile(String jsonContent, String pathToFile) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(pathToFile))) {
            printWriter.write(jsonContent);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
