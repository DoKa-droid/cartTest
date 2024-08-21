package com.example.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    By addToCartButton = By.xpath("//button[@title='Добавить в корзину']");
    By goToCartButton = By.xpath("//a[contains(text(), 'Перейти в корзину')]");
    By price = By.xpath("//span[@aria-label='Итоговая цена']");
    By notGoToCartButton = By.xpath("//span[contains(text(), 'Продолжить покупки')]");

    public void clickAddToCart() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(addToCartButton).click();
    }

    public CartPage clickGoToCart() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(goToCartButton).click();
        Thread.sleep(3000);
        return new CartPage(driver);
    }

    public void clickNotGoToCart() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(notGoToCartButton).click();
    }

    public int getPrice() throws InterruptedException {
        String calculatedSum;
        Thread.sleep(4000);
        calculatedSum = driver.findElement(price).getText();
        calculatedSum = calculatedSum.replace("\r\n", "");
        calculatedSum = calculatedSum.replace(" ", "");
        return Integer.valueOf(calculatedSum.substring(0, 3));
    };
}
