package com.saucedemo.bdd.restapitests.stepdefinitions.containers;

import com.saucedemo.bdd.restapitests.responsebodymodel.findpet.FindPetByStatusResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetFromFindPetByStatusResponseContainer {
    private FindPetByStatusResponse petByStatusResponses;
}
