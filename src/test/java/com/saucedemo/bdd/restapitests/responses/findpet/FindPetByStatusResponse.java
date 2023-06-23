package com.saucedemo.bdd.restapitests.responses.findpet;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.saucedemo.bdd.restapitests.responses.Category;
import com.saucedemo.bdd.restapitests.responses.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@JacksonXmlRootElement(localName = "Pet")
@Getter
@Setter
public class FindPetByStatusResponse {
    private long id;
    private Category category;
    private String name;
    private ArrayList<String> photoUrls;
    private ArrayList<Tag> tags;
    private String status;
}
