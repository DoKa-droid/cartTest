import com.example.common.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.awt.*;

public class CartTest extends BaseTest {

    @Test
    void cartButtonPresenceTest(){
        Assertions.assertTrue(driver.findElement(By.xpath("//a[@href='/checkout/cart/']")).isDisplayed());
    }

    @Test
    void emptyCartTest() {
        driver.findElement(By.xpath("//a[@href='/checkout/cart/']")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'В корзине нет товаров')]")).isDisplayed());
    }

    @Test
    void addToCartTest() throws InterruptedException {
        driver.findElement(By.xpath("//a[@role='button' and contains(text(), 'Войти')]")).click();
        Thread.sleep(1000);
        if (!driver.findElements(By.xpath("//input[@name='login']")).isEmpty()) {
            driver.findElement(By.xpath("//input[@name='login']")).sendKeys("stavrosivo@nalsci.com");
        } else {
            driver.findElement(By.xpath("//input[@name='Электронная почта']")).sendKeys("stavrosivo@nalsci.com");
        }
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("JieYah5puz7");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@role='button' and contains(text(), 'Войти')]")).click();

        Thread.sleep(4000);
        driver.findElement(By.xpath("//button[@type='button' and @role='button']")).click();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(Constants.PRODUCTS.PRODUCT_NAME);
        driver.findElement(By.xpath("//button[@type='button' and @role='button']")).click();

        Thread.sleep(4000);
        WebElement element = driver.findElement(By.xpath("//h2[contains(text(), 'тушь')]"));
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(), 'Тушь')]")).click();

        driver.findElement(By.xpath("//button[@title='Добавить в корзину']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(), 'Перейти в корзину')]")).click();

        Thread.sleep(4000);
        element = driver.findElement(By.xpath("//div[@id='cart']//div[contains(text(), 'Тушь для ресниц')]"));
        js.executeScript("arguments[0].scrollIntoView();", element);
        Assertions.assertTrue(element.isDisplayed());
    }

    @Test
    void deleteFromCartTest() throws InterruptedException, AWTException {
        driver.findElement(By.xpath("//a[@role='button' and contains(text(), 'Войти')]")).click();
        Thread.sleep(1000);
        if (!driver.findElements(By.xpath("//input[@name='login']")).isEmpty()) {
            driver.findElement(By.xpath("//input[@name='login']")).sendKeys("stavrosivo@nalsci.com");
        } else {
            driver.findElement(By.xpath("//input[@name='Электронная почта']")).sendKeys("stavrosivo@nalsci.com");
        }
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("JieYah5puz7");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@role='button' and contains(text(), 'Войти')]")).click();

        Thread.sleep(6000);
        driver.findElement(By.xpath("//a[@href='/checkout/cart/']")).click();
        Thread.sleep(4000);
        WebElement element = driver.findElement(By.xpath("//div[@id='cart']//div[contains(text(), 'Тушь для ресниц')]"));
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        Thread.sleep(4000);

        Robot robot = new Robot();
        robot.mouseMove(200, 220);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@type='button' and @aria-label='Убрать из корзины']")).click();
        Thread.sleep(3000);

        Assertions.assertFalse(driver.findElement(By.xpath("//div[@id='cart']//div[contains(text(), 'Тушь для ресниц')]")).isDisplayed());
    }

    @Test
    void totalCostTest() throws InterruptedException {
        int totalPrice = 0;
        String calculatedSum;
        driver.findElement(By.xpath("//a[@role='button' and contains(text(), 'Войти')]")).click();
        Thread.sleep(1000);
        if (!driver.findElements(By.xpath("//input[@name='login']")).isEmpty()) {
            driver.findElement(By.xpath("//input[@name='login']")).sendKeys("stavrosivo@nalsci.com");
        } else {
            driver.findElement(By.xpath("//input[@name='Электронная почта']")).sendKeys("stavrosivo@nalsci.com");
        }
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("JieYah5puz7");
        driver.findElement(By.xpath("//button[@role='button' and contains(text(), 'Войти')]")).click();

        Thread.sleep(4000);
        driver.findElement(By.xpath("//button[@type='button' and @role='button']")).click();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(Constants.PRODUCTS.PRODUCT_NAME);
        driver.findElement(By.xpath("//button[@type='button' and @role='button']")).click();

        Thread.sleep(4000);
        WebElement element = driver.findElement(By.xpath("//h2[contains(text(), 'тушь')]"));
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(), 'Тушь')]")).click();

        Thread.sleep(4000);
        calculatedSum = driver.findElement(By.xpath("//span[@aria-label='Итоговая цена']")).getText();
        calculatedSum = calculatedSum.replace("\r\n", "");
        calculatedSum = calculatedSum.substring(6);
        calculatedSum = calculatedSum.replace(" ", "");
        totalPrice += Integer.valueOf(calculatedSum.substring(0, 3));

        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@title='Добавить в корзину']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(), 'Продолжить покупки')]")).click();
        Thread.sleep(1000);

        calculatedSum = driver.findElement(By.xpath("//span[@aria-label='Итоговая цена']")).getText();
        calculatedSum = calculatedSum.replace("\r\n", "");
        calculatedSum = calculatedSum.substring(6);
        calculatedSum = calculatedSum.replace(" ", "");
        totalPrice += Integer.valueOf(calculatedSum.substring(0, 3));

        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@title='Добавить в корзину']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(), 'Перейти в корзину')]")).click();

        Thread.sleep(4000);

        js = (JavascriptExecutor) driver;
        element = driver.findElement(By.xpath("//div[contains(text(), 'Красота')]"));
        js.executeScript("arguments[0].scrollIntoView();", element);
        Thread.sleep(3000);
        calculatedSum = driver.findElement(By.xpath("//p[contains(text(), 'Итого')]")).getText();
        calculatedSum = calculatedSum.replace("\r\n", "");
        calculatedSum = calculatedSum.replace(" ", "");
        Assertions.assertEquals(totalPrice, Integer.valueOf(calculatedSum.substring(25, 28)));
    }

    @Test
    void amountOfProductsTest() throws InterruptedException {
        int totalAmount, productsInCart;
        driver.findElement(By.xpath("//a[@role='button' and contains(text(), 'Войти')]")).click();
        Thread.sleep(1000);
        if (!driver.findElements(By.xpath("//input[@name='login']")).isEmpty()) {
            driver.findElement(By.xpath("//input[@name='login']")).sendKeys("stavrosivo@nalsci.com");
        } else {
            driver.findElement(By.xpath("//input[@name='Электронная почта']")).sendKeys("stavrosivo@nalsci.com");
        }
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("JieYah5puz7");
        driver.findElement(By.xpath("//button[@role='button' and contains(text(), 'Войти')]")).click();

        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@href='/checkout/cart/']")).click();
        Thread.sleep(5000);

        totalAmount = Integer.parseInt(driver.findElement(By.xpath("//a[@href='/checkout/cart/']")).getText().substring(0, 1));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//div[contains(text(), 'Красота')]"));
        js.executeScript("arguments[0].scrollIntoView();", element);
        Thread.sleep(3000);
        productsInCart = Integer.parseInt(driver.findElement(By.xpath("//span[@aria-label='Количество товара в корзине']")).getText().substring(0, 1));
        Assertions.assertEquals(totalAmount, productsInCart);
    }

    @Test
    void addToFavoritesTest() throws InterruptedException, AWTException {
        driver.findElement(By.xpath("//a[@role='button' and contains(text(), 'Войти')]")).click();
        Thread.sleep(1000);
        if (!driver.findElements(By.xpath("//input[@name='login']")).isEmpty()) {
            driver.findElement(By.xpath("//input[@name='login']")).sendKeys("stavrosivo@nalsci.com");
        } else {
            driver.findElement(By.xpath("//input[@name='Электронная почта']")).sendKeys("stavrosivo@nalsci.com");
        }
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("JieYah5puz7");
        driver.findElement(By.xpath("//button[@role='button' and contains(text(), 'Войти')]")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//a[@href='/checkout/cart/']")).click();
        Thread.sleep(5000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//div[contains(text(), 'Красота')]"));
        js.executeScript("arguments[0].scrollIntoView();", element);
        Thread.sleep(3000);

        Robot robot = new Robot();
        robot.mouseMove(200, 220);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@aria-label='Добавить в избранное']")).click();
        Thread.sleep(2000);

        Assertions.assertTrue(driver.findElement(By.xpath("//span[contains(text(), 'В избранном')]")).isDisplayed());
    }

    @Test
    void productInformationTest() throws InterruptedException {
        driver.findElement(By.xpath("//a[@role='button' and contains(text(), 'Войти')]")).click();
        Thread.sleep(1000);
        if (!driver.findElements(By.xpath("//input[@name='login']")).isEmpty()) {
            driver.findElement(By.xpath("//input[@name='login']")).sendKeys("stavrosivo@nalsci.com");
        } else {
            driver.findElement(By.xpath("//input[@name='Электронная почта']")).sendKeys("stavrosivo@nalsci.com");
        }
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("JieYah5puz7");
        driver.findElement(By.xpath("//button[@role='button' and contains(text(), 'Войти')]")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//a[@href='/checkout/cart/']")).click();
        Thread.sleep(5000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//div[contains(text(), 'Красота')]"));
        js.executeScript("arguments[0].scrollIntoView();", element);
        Thread.sleep(1000);

        Assertions.assertTrue(driver.findElement(By.xpath("//img[@src='//a.lmcdn.ru/img236x341/R/T/RTLABB785001_16300929_1_v1.jpg']")).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'Тушь для ресниц')]")).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.xpath("//span[@aria-label='Количество товара в корзине']")).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.xpath("//div[@class='YUJrV2RgHZLhm9WwAykfR']")).isDisplayed()); //цена

    }

}
