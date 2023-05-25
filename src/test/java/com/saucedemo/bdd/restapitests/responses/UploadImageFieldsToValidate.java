package com.saucedemo.bdd.restapitests.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class UploadImageFieldsToValidate {
    private int code;
    private String message;
}
