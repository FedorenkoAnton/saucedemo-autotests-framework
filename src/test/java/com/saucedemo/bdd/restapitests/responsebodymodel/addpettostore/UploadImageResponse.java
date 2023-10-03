package com.saucedemo.bdd.restapitests.responsebodymodel.addpettostore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class UploadImageResponse {
    private int code;
    private String type;
    private String message;
}
