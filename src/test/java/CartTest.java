import com.example.common.*;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Credentials;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.awt.*;

public class CartTest extends BaseTest {

    @Test
    void cartButtonPresenceTest() throws InterruptedException {
        WelcomePage mainPage = new WelcomePage(driver);
        Assertions.assertTrue(mainPage.isCartPresent());
    }

    @Test
    void emptyCartTest() throws InterruptedException {
        WelcomePage mainPage = new WelcomePage(driver);
        CartPage cartPage = new CartPage(driver);
        mainPage.clickCart();
        Assertions.assertTrue(cartPage.isCartEmpty());
    }

    @Test
    void addToCartTest() throws InterruptedException {
        WelcomePage mainPage = new WelcomePage(driver);
        SearchResultPage searchPage = new SearchResultPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        mainPage.signIn(Constants.CREDENTIALS.EMAIL, Constants.CREDENTIALS.PASSWORD);
        mainPage.searchProduct(Constants.PRODUCTS.PRODUCT_NAME);

        searchPage.findProduct();

        productPage.clickAddToCart();
        productPage.clickGoToCart();

        cartPage.goToProducts();
        Assertions.assertTrue(cartPage.isProductInCart());
    }

    @Test
    void deleteFromCartTest() throws InterruptedException, AWTException {
        WelcomePage mainPage = new WelcomePage(driver);
        SearchResultPage searchPage = new SearchResultPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        mainPage.signIn(Constants.CREDENTIALS.EMAIL, Constants.CREDENTIALS.PASSWORD);
        mainPage.clickCart();

        cartPage.goToProducts();
        cartPage.clickDelete();
        Assertions.assertFalse(cartPage.isProductInCart());
    }

    @Test
    void totalCostTest() throws InterruptedException {
        int totalPrice = 0;
        WelcomePage mainPage = new WelcomePage(driver);
        SearchResultPage searchPage = new SearchResultPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        mainPage.signIn(Constants.CREDENTIALS.EMAIL, Constants.CREDENTIALS.PASSWORD);

        searchPage.findProduct();

        totalPrice += productPage.getPrice();
        productPage.clickAddToCart();
        productPage.clickNotGoToCart();
        totalPrice += productPage.getPrice();
        productPage.clickAddToCart();
        productPage.clickGoToCart();

        cartPage.goToProducts();
        Assertions.assertEquals(totalPrice, cartPage.getTotalSum());
    }

    @Test
    void amountOfProductsTest() throws InterruptedException {
        int totalAmount, productsInCart;
        WelcomePage mainPage = new WelcomePage(driver);
        SearchResultPage searchPage = new SearchResultPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        mainPage.signIn(Constants.CREDENTIALS.EMAIL, Constants.CREDENTIALS.PASSWORD);

        mainPage.clickCart();

        totalAmount = cartPage.amountOfProductsOnCartImage();
        cartPage.goToProducts();

        productsInCart = cartPage.amountOfProductsOnCartPage();
        Assertions.assertEquals(totalAmount, productsInCart);
    }

    @Test
    void addToFavoritesTest() throws InterruptedException, AWTException {
        WelcomePage mainPage = new WelcomePage(driver);
        SearchResultPage searchPage = new SearchResultPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        mainPage.signIn(Constants.CREDENTIALS.EMAIL, Constants.CREDENTIALS.PASSWORD);

        mainPage.clickCart();

        cartPage.goToProducts();
        cartPage.clickaddToFavorites();
        Assertions.assertTrue(cartPage.isProductInFavorites());
    }

    @Test
    void productInformationTest() throws InterruptedException {
        WelcomePage mainPage = new WelcomePage(driver);
        CartPage cartPage = new CartPage(driver);
        mainPage.signIn(Constants.CREDENTIALS.EMAIL, Constants.CREDENTIALS.PASSWORD);
        mainPage.clickCart();
        cartPage.goToProducts();
        Assertions.assertTrue(cartPage.isAllProductInfoPresent());
    }

}
