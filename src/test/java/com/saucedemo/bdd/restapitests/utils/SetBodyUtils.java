package com.saucedemo.bdd.restapitests.utils;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import com.saucedemo.bdd.restapitests.stepdefinitions.containers.RequestBuilderContainer;
import io.restassured.http.ContentType;

import java.io.File;
import java.io.IOException;

public class SetBodyUtils {

    private static String readStringFromFile(String path) {
        File file = new File(path);
        CharSource charSource = Files.asCharSource(file, Charsets.UTF_8);

        try {
            return charSource.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setBodyToRequestByContentType(String path, ContentType contentType,
                                                     RequestBuilderContainer requestBuilderContainer) {
        if (contentType == ContentType.JSON || contentType == ContentType.XML) {
            requestBuilderContainer.getRequestSpecBuilder().setBody(readStringFromFile(path));
        } else if (contentType == ContentType.MULTIPART) {
            requestBuilderContainer.getRequestSpecBuilder().addMultiPart(new File(path));
        }
    }
}
