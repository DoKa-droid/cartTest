import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;

public class CartTest {
    WebDriver driver;
    String mascaraBrown = "Тушь для ресниц High Impact Waterproof Mascara, тон black/brown, 8 мл";

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void cartButtonPresenceTest() {
        try {
            driver.get("https://www.lamoda.ru/");
            Thread.sleep(5000);
            WebElement cart = driver.findElement(By.xpath("//a[@href='/checkout/cart/']"));
            System.out.println(cart.isDisplayed()
                    && cart.getLocation().getX() > driver.manage().window().getSize().width / 2
                    && cart.getLocation().getY() < driver.manage().window().getSize().height / 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Test
    void emptyCartTest() {
        try {
            driver.get("https://www.lamoda.ru/");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//a[@href='/checkout/cart/']")).click();
            Thread.sleep(5000);

            System.out.println(driver.findElement(By.xpath("//div[@class='_2JSp8mN_DdUcjyfOLCxGOj']")).isDisplayed());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Test
    void addToCartTest() {
        try {
            driver.get("https://www.lamoda.ru/");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//a[@class='wCjUeog4KtWw64IplV1e6 _3A5-9K2JrODjfTiazRr7pk BLS-hOSrikRnPX76_f5Xr']")).click();
            Thread.sleep(5000);
            if (!driver.findElements(By.xpath("//input[@name='login']")).isEmpty()) {
                driver.findElement(By.xpath("//input[@name='login']")).sendKeys("stavrosivo@nalsci.com");
            } else {
                driver.findElement(By.xpath("//input[@name='Электронная почта']")).sendKeys("stavrosivo@nalsci.com");
            }
            Thread.sleep(5000);
            driver.findElement(By.xpath("//input[@name='password']")).sendKeys("JieYah5puz7");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            Thread.sleep(5000);

            driver.findElement(By.xpath("//button[@class='x-button x-button_accented x-button_32 _1nlEyvyy1LHvJnhzmHYHEz']")).click();
            Thread.sleep(5000);
            driver.findElement(By.xpath("//input[@class='_3jotUx9G5izzdWD5DIoPVO _91HSD7tcHouxctDsMacBB']")).sendKeys(mascaraBrown);
            Thread.sleep(5000);
            driver.findElement(By.xpath("//button[@class='x-button x-button_accented x-button_32 _1nlEyvyy1LHvJnhzmHYHEz']")).click();
            Thread.sleep(5000);

            driver.findElement(By.xpath("//span[@class='price']")).click();
            Thread.sleep(5000);

            driver.findElement(By.xpath("//button[@title='Добавить в корзину']")).click();
            Thread.sleep(5000);
            driver.findElement(By.xpath("//a[@href='/checkout/cart/?add=RTLABB785001NS00&popup=y']")).click();
            Thread.sleep(5000);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(By.xpath("//div[@class='_23lAKtqBbUlCSaXvUj7r6W']"));
            js.executeScript("arguments[0].scrollIntoView();", element);
            Thread.sleep(3000);
            System.out.println(element.isDisplayed());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Test
    void deleteFromCartTest() {
        try {
            driver.get("https://www.lamoda.ru/");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//a[@class='wCjUeog4KtWw64IplV1e6 _3A5-9K2JrODjfTiazRr7pk BLS-hOSrikRnPX76_f5Xr']")).click();
            Thread.sleep(5000);
            if (!driver.findElements(By.xpath("//input[@name='login']")).isEmpty()) {
                driver.findElement(By.xpath("//input[@name='login']")).sendKeys("stavrosivo@nalsci.com");
            } else {
                driver.findElement(By.xpath("//input[@name='Электронная почта']")).sendKeys("stavrosivo@nalsci.com");
            }
            Thread.sleep(5000);
            driver.findElement(By.xpath("//input[@name='password']")).sendKeys("JieYah5puz7");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            Thread.sleep(10000);

            driver.findElement(By.xpath("//a[@href='/checkout/cart/']")).click();
            Thread.sleep(5000);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(By.xpath("//div[@class='_23lAKtqBbUlCSaXvUj7r6W']"));
            js.executeScript("arguments[0].scrollIntoView();", element);
            Thread.sleep(5000);

            Robot robot = new Robot();
            robot.mouseMove(200, 220);
            Thread.sleep(5000);
            driver.findElement(By.xpath("//button[@class='_3ACQxVf6uaaBQd_yEZwGV_']")).click();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Test
    void totalCostTest() {
        try {
            driver.get("https://www.lamoda.ru/");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//a[@class='wCjUeog4KtWw64IplV1e6 _3A5-9K2JrODjfTiazRr7pk BLS-hOSrikRnPX76_f5Xr']")).click();
            Thread.sleep(5000);
            if (!driver.findElements(By.xpath("//input[@name='login']")).isEmpty()) {
                driver.findElement(By.xpath("//input[@name='login']")).sendKeys("stavrosivo@nalsci.com");
            } else {
                driver.findElement(By.xpath("//input[@name='Электронная почта']")).sendKeys("stavrosivo@nalsci.com");
            }
            Thread.sleep(5000);
            driver.findElement(By.xpath("//input[@name='password']")).sendKeys("JieYah5puz7");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            Thread.sleep(5000);

            driver.findElement(By.xpath("//a[@href='/checkout/cart/']")).click();
            Thread.sleep(5000);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(By.xpath("//div[@class='_2a4wemC96MmQ64tgkwjoNp _2s9wnoJIkBEIzjLM2N9CZi']"));
            js.executeScript("arguments[0].scrollIntoView();", element);
            Thread.sleep(3000);
            String calculatedSum = element.getText();
            calculatedSum = calculatedSum.replace("\r\n", "");
            calculatedSum = calculatedSum.substring(6);
            calculatedSum = calculatedSum.replace(" ", "");
            Assertions.assertEquals(5557, Integer.valueOf(calculatedSum.substring(1, 5)));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Test
    void amountOfProductsTest() {
        try {
            driver.get("https://www.lamoda.ru/");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//a[@class='wCjUeog4KtWw64IplV1e6 _3A5-9K2JrODjfTiazRr7pk BLS-hOSrikRnPX76_f5Xr']")).click();
            Thread.sleep(5000);
            if (!driver.findElements(By.xpath("//input[@name='login']")).isEmpty()) {
                driver.findElement(By.xpath("//input[@name='login']")).sendKeys("stavrosivo@nalsci.com");
            } else {
                driver.findElement(By.xpath("//input[@name='Электронная почта']")).sendKeys("stavrosivo@nalsci.com");
            }
            Thread.sleep(5000);
            driver.findElement(By.xpath("//input[@name='password']")).sendKeys("JieYah5puz7");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            Thread.sleep(5000);

            driver.findElement(By.xpath("//a[@href='/checkout/cart/']")).click();
            Thread.sleep(5000);

            int totalAmount = Integer.valueOf(driver.findElement(By.xpath("//a[@href='/checkout/cart/']")).getText().substring(0, 1));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(By.xpath("//div[@class='gNnAk3S-lnF4jt96vc0D4']"));
            js.executeScript("arguments[0].scrollIntoView();", element);
            Thread.sleep(3000);
            Assertions.assertEquals(totalAmount, 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Test
    void addToFavoritesTest() {
        try {
            driver.get("https://www.lamoda.ru/");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//a[@class='wCjUeog4KtWw64IplV1e6 _3A5-9K2JrODjfTiazRr7pk BLS-hOSrikRnPX76_f5Xr']")).click();
            Thread.sleep(5000);
            if (!driver.findElements(By.xpath("//input[@name='login']")).isEmpty()) {
                driver.findElement(By.xpath("//input[@name='login']")).sendKeys("stavrosivo@nalsci.com");
            } else {
                driver.findElement(By.xpath("//input[@name='Электронная почта']")).sendKeys("stavrosivo@nalsci.com");
            }
            Thread.sleep(5000);
            driver.findElement(By.xpath("//input[@name='password']")).sendKeys("JieYah5puz7");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            Thread.sleep(5000);

            driver.findElement(By.xpath("//a[@href='/checkout/cart/']")).click();
            Thread.sleep(5000);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(By.xpath("//div[@class='_23lAKtqBbUlCSaXvUj7r6W']"));
            js.executeScript("arguments[0].scrollIntoView();", element);
            Thread.sleep(3000);

            Robot robot = new Robot();
            robot.mouseMove(200, 220);
            Thread.sleep(5000);
            driver.findElement(By.xpath("//div[@aria-label='Добавить в избранное']")).click();
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Test
    void productInformationTest() {
        try {
            driver.get("https://www.lamoda.ru/");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//a[@class='wCjUeog4KtWw64IplV1e6 _3A5-9K2JrODjfTiazRr7pk BLS-hOSrikRnPX76_f5Xr']")).click();
            Thread.sleep(5000);
            if (!driver.findElements(By.xpath("//input[@name='login']")).isEmpty()) {
                driver.findElement(By.xpath("//input[@name='login']")).sendKeys("stavrosivo@nalsci.com");
            } else {
                driver.findElement(By.xpath("//input[@name='Электронная почта']")).sendKeys("stavrosivo@nalsci.com");
            }
            Thread.sleep(5000);
            driver.findElement(By.xpath("//input[@name='password']")).sendKeys("JieYah5puz7");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            Thread.sleep(5000);

            driver.findElement(By.xpath("//a[@href='/checkout/cart/']")).click();
            Thread.sleep(5000);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(By.xpath("//div[@class='gNnAk3S-lnF4jt96vc0D4']"));
            js.executeScript("arguments[0].scrollIntoView();", element);
            Thread.sleep(3000);

            Assertions.assertTrue(driver.findElement(By.xpath("//a[@href='/p/rtlabb785001/']")).isDisplayed());
            Assertions.assertTrue(driver.findElement(By.xpath("//div[@class='_23lAKtqBbUlCSaXvUj7r6W']")).isDisplayed());
            Assertions.assertTrue(driver.findElement(By.xpath("//span[@aria-label='Количество товара в корзине']")).isDisplayed());
            Assertions.assertTrue(driver.findElement(By.xpath("//div[@class='YUJrV2RgHZLhm9WwAykfR']")).isDisplayed());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

}
