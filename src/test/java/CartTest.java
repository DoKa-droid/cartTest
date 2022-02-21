import com.example.common.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import javax.xml.crypto.dsig.spec.XPathFilterParameterSpec;
import java.awt.*;

public class CartTest extends BaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/searches.csv", numLinesToSkip = 1)
    void correctProductNameTest(String query) throws InterruptedException {
        WelcomePage mainPage = new WelcomePage(driver);
        SearchResultPage searchPage = new SearchResultPage(driver);

        mainPage.searchProduct(query);
        searchPage.clickCross();
        Assertions.assertTrue(searchPage.findProduct(query));
    }

}
