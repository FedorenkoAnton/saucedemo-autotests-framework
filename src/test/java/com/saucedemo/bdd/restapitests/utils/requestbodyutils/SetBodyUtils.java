package com.saucedemo.bdd.restapitests.utils.requestbodyutils;

import com.saucedemo.bdd.restapitests.stepdefinitions.containers.RequestBuilderContainer;
import io.restassured.http.ContentType;

import java.io.File;

public class SetBodyUtils extends BasicRequestBodyUtils {

    public void setBodyToRequestByContentType(String path, ContentType contentType,
                                              RequestBuilderContainer requestBuilderContainer) {
        if (contentType == ContentType.JSON || contentType == ContentType.XML) {
            requestBuilderContainer.getRequestSpecBuilder().setBody(readStringFromFile(path));
        } else if (contentType == ContentType.MULTIPART) {
            requestBuilderContainer.getRequestSpecBuilder().addMultiPart(new File(path));
        }
    }
}
