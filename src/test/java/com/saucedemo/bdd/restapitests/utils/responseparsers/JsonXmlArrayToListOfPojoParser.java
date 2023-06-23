package com.saucedemo.bdd.restapitests.utils.responseparsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

public class JsonXmlArrayToListOfPojoParser extends ResponseParser {

    private  <T> List<T> parseJsonArrayToListOfPojo(Response response, TypeReference<List<T>> typeReference) {
        String responseJson =  response.getBody().asString();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(responseJson, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private  <T> List<T> parseXmlArrayToListOfPojo(Response response, TypeReference<List<T>> typeReference) {
        String responseJson =  response.getBody().asString();
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(responseJson, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> parseXmlOrJsonArrayToListOfPojoByMediaType(Response response,
                                                                  TypeReference<List<T>> typeReference) {
        ContentType contentType = ContentType.fromContentType(response.getContentType());

        if (contentType == ContentType.JSON) {
            return parseJsonArrayToListOfPojo(response, typeReference);
        } else if (contentType == ContentType.XML) {
           return parseXmlArrayToListOfPojo(response, typeReference);
        } else {
            throw new IllegalArgumentException(String.format("Unsupported content type: %s", contentType.toString()));
        }
    }
}
