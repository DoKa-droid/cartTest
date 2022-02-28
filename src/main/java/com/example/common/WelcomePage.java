package com.example.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class WelcomePage {
    WebDriver driver;

    public WelcomePage(WebDriver driver) {
        this.driver = driver;
    }

    By cartButton = By.xpath("//a[@href='/checkout/cart/']");
    By signInButton = By.xpath("//a[@role='button' and contains(text(), 'Войти')]");
    By loginField = By.xpath("//input[@name='login']");
    By emailField = By.xpath("//input[@name='Электронная почта']");
    By passwordField = By.xpath("//input[@name='password']");
    By signInWindowButton = By.xpath("//button[@role='button' and contains(text(), 'Войти')]");
    By searchButton = By.xpath("//button[@type='button' and @role='button']");
    By searchField = By.xpath("//input[@type='text']");

    public CartPage clickCart() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(cartButton).click();
        Thread.sleep(3000);
        return new CartPage(driver);
    }

    public boolean isCartPresent() throws InterruptedException {
        Thread.sleep(2000);
        return driver.findElements(cartButton).size() > 0;
    }

    public void clickSignIn() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(signInButton).click();
    }

    public void enterLogin(String login) throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(loginField).sendKeys(login);
    }

    public void enterEmail(String email) throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignInWindow() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(signInWindowButton).click();
    }

    public void clickSearch() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(searchButton).click();
    }

    public void searchInSearchField(String query) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(searchField).sendKeys(query);
    }

    public WelcomePage signIn(String login, String password) throws InterruptedException {
        clickSignIn();
        if (driver.findElements(loginField).size() > 0) {
            enterLogin(login);
        } else {
            enterEmail(login);
        }
        enterPassword(password);
        clickSignInWindow();
        return new WelcomePage(driver);
    }

    public SearchResultPage searchProduct(String query) throws InterruptedException {
        clickSearch();
        searchInSearchField(query);
        clickSearch();
        return new SearchResultPage(driver);
    }
}
