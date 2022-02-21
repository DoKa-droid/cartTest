import com.example.common.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get(Constants.URL.BASE_URL);
        driver.manage().window().maximize();
        Thread.sleep(2000);
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
