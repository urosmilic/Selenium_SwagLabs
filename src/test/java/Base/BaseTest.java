package Base;

import Pages.CartPage;
import Pages.LoginPage;
import Pages.ProductsPage;
import Pages.SauceLabsOnesie_item_Page;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    public WebDriver driver;
    public WebDriverWait wdwait;
    public ExcelReader excelReader;
    public LoginPage loginPage;
    public ProductsPage productsPage;
    public CartPage cartPage;
    public SauceLabsOnesie_item_Page sauceLabsOnesie_item_page;
    public JavascriptExecutor js;

    @BeforeClass
    public void setUp () throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wdwait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        sauceLabsOnesie_item_page = new SauceLabsOnesie_item_Page(driver);
        cartPage = new CartPage(driver);
        excelReader = new ExcelReader("C:\\Users\\USER\\Desktop\\Saucedo.xlsx");
        js = (JavascriptExecutor) driver;
    }

    @AfterClass
    public void tearDown () {
        driver.close();
        driver.quit();
    }

    public void scrollIntoView (WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

}
