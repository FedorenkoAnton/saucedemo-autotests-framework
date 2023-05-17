package com.saucedemo.bdd.restapitests.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {

    public static String readFromFile(String path) {
        StringBuilder jsonFromFile = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonFromFile.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonFromFile.toString();
    }
}
