package com.saucedemo.bdd.restapitests.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "Pet")
@Getter
@Setter
public class AddNewPetFieldsToValidate {
    @JacksonXmlProperty(localName = "id")
    private int id;

    @JacksonXmlProperty(localName = "name")
    private String name;
}
