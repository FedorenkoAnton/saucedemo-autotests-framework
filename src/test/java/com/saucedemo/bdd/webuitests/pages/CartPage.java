package com.saucedemo.bdd.webuitests.pages;

import com.codeborne.selenide.SelenideElement;
import com.saucedemo.bdd.webuitests.utils.CartPageUtils;
import com.saucedemo.bdd.webuitests.utils.MainPageUtil;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CartPage extends BasePage {

    private final static String CHECKOUT_BUTTON_XPATH = "//button[@name='checkout']";

    protected void clickOnCheckoutButton() {
        clickButton(CHECKOUT_BUTTON_XPATH);
    }

    protected void checkAddedProductsOnCartPage() {
        List<String> productsInCart = CartPageUtils.getNamesOfProductsInCart();
        List<String> addedProducts = MainPageUtil.createListWithNamesOfAddedProducts();
        String mismatchMessagePattern = "Products in cart differs from added products: %s";
        assertThat(String.format(mismatchMessagePattern, productsInCart),
                productsInCart, equalTo(addedProducts));
    }

    protected void removeProductFromCart(String productToRemoveName) {
        String removeButtonXPathPattern =
                "//div[text()='%s']/../following-sibling::div[@class='item_pricebar']//button";
        clickButton(String.format(removeButtonXPathPattern, productToRemoveName));
    }

    protected void checkProductRemovedFromCart(String productToRemoveName) {
        List<String> productsInCart = CartPageUtils.getNamesOfProductsInCart();
        String mismatchMessagePattern = "%s is in the cart: %s";
        assertThat(String.format(mismatchMessagePattern, productToRemoveName, productsInCart),
                productsInCart, is(not(hasItem(productToRemoveName))));
    }

    protected void validateCheckoutButtonIsDisabledIfCartIsEmpty() {
        SelenideElement checkoutButton = $(By.xpath(CHECKOUT_BUTTON_XPATH));
        assertThat("'Checkout' button is enabled", checkoutButton.is(disabled));
    }
}
