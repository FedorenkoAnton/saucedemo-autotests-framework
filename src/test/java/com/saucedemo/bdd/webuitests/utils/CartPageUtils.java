package com.saucedemo.bdd.webuitests.utils;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$;

public class CartPageUtils {

    public static List<String> getNamesOfProductsInCart() {
        List<SelenideElement> products = $$(By.xpath("//div[@class='inventory_item_name']"));

        return  products.stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
    }
}
