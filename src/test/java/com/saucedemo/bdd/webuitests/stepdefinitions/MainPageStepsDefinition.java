package com.saucedemo.bdd.webuitests.stepdefinitions;

import com.saucedemo.bdd.webuitests.pages.MainPage;
import com.saucedemo.bdd.utils.MainPageUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;
import java.util.Objects;

public class MainPageStepsDefinition extends MainPage {
    private final Map<String, String> products = MainPageUtil.createMapWithProducts();

    @And("clicks on Cart icon")
    public void clicksOnCartIcon() {
        clickOnCartIcon();
    }

    @When("^user adds '([^\"]*)' to the cart$")
    public void userAddsProductToTheCart(String productName) {
        clickOnAddProduct(products, productName);
    }

    @And("clicks on Product sort dropdown")
    public void clicksOnProductSortDropdown() {
        clickOnProductSortDropDown();
    }

    @When("^chooses option ([^\"]*)$")
    public void choosesOptionNAMEZTOA(String sortingOption) {
        if (Objects.equals(sortingOption, "NAME Z TO A")) {
            clickOnZToAOption();
        } else if (Objects.equals(sortingOption, "price low to high")) {
            clickOnSortOptionFromHighToLowPrice();
        } else {
            throw new IllegalArgumentException(String.format("Unknown sorting option: %s", sortingOption));
        }
    }

    @Then("^products should be ordered (in alphabetically descending order|by price from low to high)$")
    public void productsShouldBeOrderedInAlphabeticallyDescendingOrder(String orderingType) {
        if (Objects.equals(orderingType, "in alphabetically descending order")) {
            checkProductsOrderedAlphabeticallyInDescendingOrder();
        } else if (Objects.equals(orderingType, "by price from low to high")) {
            checkProductsOrderedByPriceInDescendingOrder();
        } else {
            throw new IllegalArgumentException(String.format("Unknown ordering type: %s", orderingType));
        }
    }
}
