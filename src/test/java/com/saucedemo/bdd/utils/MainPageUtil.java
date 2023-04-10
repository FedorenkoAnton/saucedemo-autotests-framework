package com.saucedemo.bdd.utils;

import com.codeborne.selenide.SelenideElement;
import com.saucedemo.bdd.enums.OrderOptions;
import org.openqa.selenium.By;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$;

public class MainPageUtil {

    private final static String PRODUCT_NAME_XPATH = "//div[@class='inventory_item_name']";
    private final static String PRODUCT_PRICE_XPATH = "//div[@class='inventory_item_price']";

    public static Map<String, String> createMapWithProducts() {
        Map<String, String> products = new LinkedHashMap<>();
        products.put("Sauce Labs Backpack", "//button[@id='add-to-cart-sauce-labs-backpack']");
        products.put("Sauce Labs Fleece Jacket", "//button[@id='add-to-cart-sauce-labs-fleece-jacket']");
        products.put("Sauce Labs Onesie", "//button[@id='add-to-cart-sauce-labs-onesie']");

        return products;
    }

    public static List<String> createListWithNamesOfAddedProducts() {
        return List.of("Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie");
    }

    public static List<String> getProductNames() {
        List<SelenideElement> productNames = getProductsTileValue(PRODUCT_NAME_XPATH);

        return productNames.stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
    }

    public static <T extends Comparable<? super T>> List<T> getOrderedProductsList(String orderOption, List<T> products) {
        if (OrderOptions.from(orderOption) == OrderOptions.DESCENDING) {
            products.sort(Collections.reverseOrder());
        } else {
            Collections.sort(products);
        }

        return products;
    }

    public static List<Float> getProductsPrices() {
        List<SelenideElement> productPrices = getProductsTileValue(PRODUCT_PRICE_XPATH);

        return productPrices.stream()
                .map(SelenideElement::getText)
                .map(productPrice -> productPrice.replaceAll("\\$", ""))
                .map(Float::parseFloat)
                .collect(Collectors.toList());
    }

    private static List<SelenideElement> getProductsTileValue(String xPath) {
        return $$(By.xpath(xPath));
    }
}
