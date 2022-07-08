package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    WebElement usernameTextbox;
    WebElement passwordtextbox;
    WebElement loginButton;

    public WebElement getUsernameTextbox() {
        return driver.findElement(By.id("user-name"));
    }

    public WebElement getPasswordtextbox() {
        return driver.findElement(By.id("password"));
    }

    public WebElement getLoginButton() {
        return driver.findElement(By.id("login-button"));
    }

    //--------------------------------------------------------

    public void  enterUsername (String username) {
        getUsernameTextbox().sendKeys(username);
    }
    public void enterPassword (String password) {
        getPasswordtextbox().sendKeys(password);
    }
    public void clickOnLoginButton () {
        getLoginButton().click();
    }

}
