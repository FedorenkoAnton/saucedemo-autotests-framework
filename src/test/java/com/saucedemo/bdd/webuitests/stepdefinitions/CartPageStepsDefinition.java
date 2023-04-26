package com.saucedemo.bdd.webuitests.stepdefinitions;

import com.saucedemo.bdd.webuitests.pages.CartPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class CartPageStepsDefinition extends CartPage {

    @And("clicks on Checkout button")
    public void clicksOnCheckoutButton() {
        clickOnCheckoutButton();
    }

    @Then("added products are displayed on the cart page")
    public void addedProductsAreDisplayedOnTheCartPage() {
        checkAddedProductsOnCartPage();
    }

    @And("user remove {string} from the cart")
    public void userRemoveProductFromTheCart(String productToRemoveName) {
        removeProductFromCart(productToRemoveName);
    }

    @Then("{string} is no longer in the cart")
    public void removedProductIsNoLongerInTheCart(String removedProductName) {
        checkProductRemovedFromCart(removedProductName);
    }

    @Then("Checkout button should be disabled")
    public void checkoutButtonShouldBeDisabled() {
        validateCheckoutButtonIsDisabledIfCartIsEmpty();
    }
}
