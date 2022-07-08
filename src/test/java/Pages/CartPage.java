package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    WebElement checkoutButton;
    WebElement continueShopping;
    WebElement cartTitle;
    WebElement quantityBox;
    WebElement productPrice;
    List<WebElement> listRemoveButtons;

    public WebElement getCheckoutButton() {
        return driver.findElement(By.xpath("//button[@name = 'checkout']"));
    }

    public WebElement getContinueShopping() {
        return driver.findElement(By.id("continue-shopping"));
    }

    public WebElement getCartTitle() {
        return driver.findElement(By.className("title"));
    }

    public WebElement getQuantityBox() {
        return driver.findElement(By.xpath("(//div[@class=cart_quantity])[2]"));
    }

    public WebElement getProductPrice() {
        return driver.findElement(By.xpath("//div[text()='29.99']"));
    }

    public  void chooseNumerOfRemoveButtonToClick (int number) {
        listRemoveButtons = driver.findElements(By.cssSelector(".btn.btn_secondary.btn_small.cart_button"));
        listRemoveButtons.get(number-1).click();
        /*for (int i = 0; i < listRemoveButtons.size(); i++) {
            if (i == number) {
                listRemoveButtons.get(i).click();
                break;
            }
        }*/
    }









}
