package com.saucedemo.bdd.restapitests.responsebodymodel.store;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@JacksonXmlRootElement(localName = "Order")
@Getter
@Setter
public class PlaceOrderForPetResponse {
    private long id;
    private long petId;
    private int quantity;
    private Date shipDate;
    private String status;
    private boolean complete;
}
