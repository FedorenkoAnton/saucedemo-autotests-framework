package com.saucedemo.bdd.restapitests.responsebodymodel.addpettostore;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.saucedemo.bdd.restapitests.responsebodymodel.Category;
import com.saucedemo.bdd.restapitests.responsebodymodel.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@JacksonXmlRootElement(localName = "Pet")
@Getter
@Setter
public class UpdatePetResponse {
    private long id;
    private Category category;
    private String name;
    private ArrayList<String> photoUrls;
    private ArrayList<Tag> tags;
    private String status;
}
