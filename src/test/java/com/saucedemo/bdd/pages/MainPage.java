package com.saucedemo.bdd.pages;

import com.saucedemo.bdd.utils.MainPageUtil;
import org.hamcrest.Matchers;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

public class MainPage extends BasePage {

    private final static String SAUCE_LABS_BACKPACK_ADD_TO_CART_BUTTON_XPATH =
            "//button[@name='add-to-cart-sauce-labs-backpack']";
    private final static String CART_ICON_XPATH = "//a[@class='shopping_cart_link']";
    private final static String PRODUCT_SORT_DROPDOWN_XPATH = "//select[@class='product_sort_container']";
    private final static String Z_TO_A_SORT_OPTION_XPATH = "//option[@value='za']";
    private final static String HIGH_TO_LOW_PRICE_SORT_OPTION_XPATH = "//option[@value='hilo']";

    protected void clickOnCartIcon() {
        clickButton(CART_ICON_XPATH);
    }

    protected void clickOnAddProduct(Map<String, String> products, String productName) {
        clickButton(products.get(productName));
    }

    protected void clickOnProductSortDropDown() {
        clickButton(PRODUCT_SORT_DROPDOWN_XPATH);
    }

    protected void clickOnZToAOption() {
        clickButton(Z_TO_A_SORT_OPTION_XPATH);
    }

    protected void clickOnSortOptionFromHighToLowPrice() {
        clickButton(HIGH_TO_LOW_PRICE_SORT_OPTION_XPATH);
    }

    protected void checkProductsOrderedAlphabeticallyInDescendingOrder() {
        List<String> productsAfterSorting = MainPageUtil.getProductNames();
        List<String> expectedProductsOrder = MainPageUtil.getOrderedProductsList("descending",
                MainPageUtil.getProductNames());
        assertThat("Products are not sorted in descending order by names",
                productsAfterSorting, Matchers.equalTo(expectedProductsOrder));
    }

    protected void checkProductsOrderedByPriceInDescendingOrder() {
        List<Float> productsAfterSortingByPrice = MainPageUtil.getProductsPrices();
        List<Float> expectedProductsOrder = MainPageUtil.getOrderedProductsList("descending",
                MainPageUtil.getProductsPrices());
        assertThat("Products are not sorted in descending order by price",
                productsAfterSortingByPrice, Matchers.equalTo(expectedProductsOrder));
    }

}
