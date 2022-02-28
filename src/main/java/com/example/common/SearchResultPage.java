package com.example.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class SearchResultPage {
    WebDriver driver;
    JavascriptExecutor js;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    By searchTitle = By.xpath("//h2[contains(text(), 'тушь')]");
    By productDescription = By.xpath("//span[contains(text(), 'Тушь')]");

    public void clickProduct() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(productDescription).click();
    }

    public void scrollToProduct() throws InterruptedException {
        Thread.sleep(4000);
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", searchTitle);
    }

    public ProductPage findProduct() throws InterruptedException {
        scrollToProduct();
        clickProduct();
        return new ProductPage(driver);
    }
}
