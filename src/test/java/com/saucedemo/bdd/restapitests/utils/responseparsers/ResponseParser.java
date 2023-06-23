package com.saucedemo.bdd.restapitests.utils.responseparsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ResponseParser {

    private  <T> T getJsonResponseAsObject(Response response, String path, Class<T> targetClass) {
        return response.jsonPath().getObject(path, targetClass);
    }

    private  <T> T getJsonResponseAsObject(Response response, Class<T> targetClass) {
        return this.getJsonResponseAsObject(response, "", targetClass);
    }

    private <T> T getXmlResponseAsObject(Response response, Class<T> targetClass) {
        String xmlResponseAsString = response.getBody().asString();
        XmlMapper xmlMapper = new XmlMapper();

        try {
            return xmlMapper.readValue(xmlResponseAsString, targetClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getResponseAsObject(Response response, Class<T> targetClass) {
        ContentType contentType = ContentType.fromContentType(response.getContentType());

        if (contentType == ContentType.JSON) {
            return getJsonResponseAsObject(response, targetClass);
        } else if (contentType == ContentType.XML) {
            return getXmlResponseAsObject(response, targetClass);
        } else {
            throw new IllegalArgumentException(String.format("Unsupported content type: %s", contentType.toString()));
        }
    }

}
