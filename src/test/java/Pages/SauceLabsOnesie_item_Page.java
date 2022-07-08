package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SauceLabsOnesie_item_Page {
    WebDriver driver;

    public SauceLabsOnesie_item_Page(WebDriver driver) {
        this.driver = driver;
    }

    WebElement productTitle;
    WebElement addToCartButton;
    WebElement getProductPrice;
    WebElement cart;
    WebElement removeFromCart;

    public WebElement getProductTitle() {
        return driver.findElement(By.cssSelector(".inventory_details_name.large_size"));
    }

    public WebElement getAddToCartButton() {
        return driver.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));
    }

    public WebElement getRemoveFromCart() {
        return driver.findElement(By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory"));
    }

    public WebElement getGetProductPrice() {
        return driver.findElement(By.className("inventory_details_price"));
    }

    public WebElement getCart() {
        return driver.findElement(By.className("shopping_cart_link"));
    }

    public void clickOnAddToCart () {
        getAddToCartButton().click();
    }

    public void clickOnRemoveFromCart () {
        getRemoveFromCart().click();
    }

}
