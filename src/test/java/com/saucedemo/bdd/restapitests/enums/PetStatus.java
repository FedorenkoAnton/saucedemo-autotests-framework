package com.saucedemo.bdd.restapitests.enums;

import java.util.Arrays;
import java.util.Objects;

public enum PetStatus {
    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    public final String value;

    private PetStatus(String value) {
        this.value = value;
    }

    public static PetStatus from(String petStatusValue) {
        return Arrays.stream(PetStatus.values())
                .filter(petStatus -> Objects.equals(petStatus.value, petStatusValue))
                .findFirst()
                .orElseThrow();
    }
}
