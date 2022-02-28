package com.example.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public class CartPage {
    WebDriver driver;
    JavascriptExecutor js;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    By cartButton = By.xpath("//a[@href='/checkout/cart/']");
    By emptyCartText = By.xpath("//div[contains(text(), 'В корзине нет товаров')]");
    By categoryTitle = By.xpath("//div[contains(text(), 'Красота')]");
    By amountOfProduct = By.xpath("//span[@aria-label='Количество товара в корзине']");
    By image = By.xpath("//img[@src='//a.lmcdn.ru/img236x341/R/T/RTLABB785001_16300929_1_v1.jpg']");
    By name = By.xpath("//div[contains(text(), 'Тушь для ресниц')]");
    By price = By.xpath("//div[@class='YUJrV2RgHZLhm9WwAykfR']");
    By addToFavoritesButton = By.xpath("//div[@aria-label='Добавить в избранное']");
    By addedToFavorites = By.xpath("//span[contains(text(), 'В избранном')]");
    By deleteButton = By.xpath("//button[@type='button' and @aria-label='Убрать из корзины']");
    By finalCart = By.xpath("//p[contains(text(), 'Итого')]");

    public void clickDelete() throws InterruptedException, AWTException {
        Thread.sleep(2000);
        Robot robot = new Robot();
        robot.mouseMove(200, 220);
        Thread.sleep(1000);
        driver.findElement(deleteButton).click();
    }

    public void clickaddToFavorites() throws InterruptedException, AWTException {
        Thread.sleep(2000);
        Robot robot = new Robot();
        robot.mouseMove(200, 220);
        Thread.sleep(1000);
        driver.findElement(addToFavoritesButton).click();
    }

    public boolean isCartEmpty() throws InterruptedException {
        Thread.sleep(2000);
        return driver.findElements(emptyCartText).size() > 0;
    }

    public void goToProducts() throws InterruptedException {
        Thread.sleep(2000);
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(categoryTitle));
    }

    public boolean isAllProductInfoPresent() throws InterruptedException {
        Thread.sleep(2000);
        return (driver.findElements(image).size() > 0
                && driver.findElements(name).size() > 0
                && driver.findElements(amountOfProduct).size() > 0
                && driver.findElements(price).size() > 0);
    }

    public boolean isProductInCart() throws InterruptedException {
        Thread.sleep(2000);
        return driver.findElements(name).size() > 0;
    }

    public boolean isProductInFavorites() {
        return driver.findElements(addedToFavorites).size() > 0;
    }

    public int getTotalSum() throws InterruptedException {
        String calculatedSum;
        Thread.sleep(4000);
        calculatedSum = driver.findElement(finalCart).getText();
        calculatedSum = calculatedSum.replace("\r\n", "");
        calculatedSum = calculatedSum.replace(" ", "");
        return Integer.valueOf(calculatedSum.substring(20, 23));
    }

    public int amountOfProductsOnCartImage(){
        return Integer.parseInt(driver.findElement(cartButton).getText().substring(0, 1));
    }
    public int amountOfProductsOnCartPage(){
        return Integer.parseInt(driver.findElement(amountOfProduct).getText().substring(0, 1));
    }
}
