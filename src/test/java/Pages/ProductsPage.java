package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage {
    WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    WebElement cart;
    WebElement menuButton;
    WebElement logoutTab; //located in menu
    List<WebElement> listOfItems;
    List<WebElement> listOfAddToCart;
    WebElement body;
    WebElement addToCartBackpack;
    WebElement productsHeading;

    public WebElement getCart() {
        return driver.findElement(By.id("shopping_cart_container"));
    }
    public WebElement getMenuButton() {
        return driver.findElement(By.id("react-burger-menu-btn"));
    }

    public WebElement getLogoutTab() {
        return driver.findElement(By.id("logout_sidebar_link"));
    }

    public WebElement getBody() {
        return driver.findElement(By.xpath("//*[@id=\"inventory_container\"]"));
    }

    public WebElement getProductsHeading() {
        return driver.findElement(By.className("title"));
    }

    public void selectLastSauceItem () {
        listOfItems = driver.findElements(By.className("inventory_item_name"));
        for (int i = listOfItems.size()-1; i >=0; i--) {
            if (listOfItems.get(i).getText().contains("Sauce Labs")) {//("Sauce Labs")
                listOfItems.get(i).click();
                break;
            }
        }
    }

    /*public void addSpecificItemToChart () {
        listOfAddToCart = driver.findElements(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));
        listOfAddToCart.get(this.indexOfItem()).click();
    }*/

    public WebElement getAddToCartBackpack() {
        return driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
    }
}
