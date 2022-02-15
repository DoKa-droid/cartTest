package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Cart {
    static WebDriver driver;
    String mascaraBlack = "Тушь для ресниц High Impact Waterproof Mascara, тон black, 8 мл";
    String mascaraBrown = "Тушь для ресниц High Impact Waterproof Mascara, тон black/brown, 8 мл";

    public static void autoAuthorize(){
        try {
            driver.get("https://www.lamoda.ru/");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//a[@class='wCjUeog4KtWw64IplV1e6 _3A5-9K2JrODjfTiazRr7pk BLS-hOSrikRnPX76_f5Xr']")).click();
            Thread.sleep(5000);
            driver.findElement(By.xpath("//input[@name='login']")).sendKeys("stavrosivo@nalsci.com");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//input[@name='password']")).sendKeys("JieYah5puz7");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }
}
