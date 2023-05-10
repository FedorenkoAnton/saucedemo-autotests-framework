package com.saucedemo.bdd.webuitests.enums;

import java.util.Arrays;
import java.util.Objects;

public enum OrderOptions {

    DESCENDING("descending"),
    ASCENDING("ascending");

    public final String value;

    private OrderOptions(String value) {
        this.value = value;
    }

    public static OrderOptions from(String orderOptionValue) {
        return Arrays.stream(OrderOptions.values())
                .filter(orderOption -> Objects.equals(orderOption.value, orderOptionValue))
                .findFirst()
                .orElseThrow();
    }
}
