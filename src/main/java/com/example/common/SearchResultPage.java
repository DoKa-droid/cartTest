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

    By searchTitle;
    By productDescription = By.xpath("//span[contains(text(), 'Кеды')]");
    By nothingFound = By.xpath("//div[contains(text(), 'По вашему запросу ничего не найдено')]");
    By cross = By.xpath(("//div[@class='sub-popup-feb18__close']"));

    public void clickCross() throws InterruptedException {
        Thread.sleep(2000);
        if (driver.findElements(cross).size() > 0) {
            driver.findElement(cross).click();
        }
    }

    public void scrollToProduct(By searchTitle) throws InterruptedException {
        Thread.sleep(4000);
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(searchTitle));
        Thread.sleep(2000);
    }

    public boolean findProduct(String product) throws InterruptedException {
        searchTitle = By.xpath("//h2[contains(text(), '" + product + "')]");
        if (driver.findElements(searchTitle).size() > 0) {
            scrollToProduct(searchTitle);
            return driver.findElements(productDescription).size() > 0;
        } else {
            return driver.findElements(nothingFound).size() > 0;
        }
    }
}
