package com.example.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePage {
    WebDriver driver;

    public WelcomePage(WebDriver driver) {
        this.driver = driver;
    }

    By searchButton = By.xpath("//button[@type='button' and @role='button']");
    By searchField = By.xpath("//input[@type='text']");

    public void clickSearch() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(searchButton).click();
    }

    public void searchInSearchField(String query) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(searchField).sendKeys(query);
    }

    public SearchResultPage searchProduct(String query) throws InterruptedException {
        clickSearch();
        searchInSearchField(query);
        clickSearch();
        return new SearchResultPage(driver);
    }
}
