package Test;

import Base.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.openqa.selenium.By.*;

public class Testiranje extends BaseTest {

    @BeforeMethod
    public void pageSetup () {
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
    }
    @AfterMethod
    public void deleteCookies () {
        //driver.manage().deleteAllCookies();
    }

    @Test (priority = 10, description = "validCredentials")
    public void test1 () {
        String validUsername = excelReader.getStringData("Login",1,1);
        String validPassword = excelReader.getStringData("Login", 1,2);

        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(validPassword);
        loginPage.clickOnLoginButton();
        boolean check = false;
        try {
            loginPage.getLoginButton().isDisplayed();
        } catch (Exception e) {
            check = false;
        }
        Assert.assertFalse(check);
        Assert.assertTrue(productsPage.getCart().isDisplayed());
        Assert.assertTrue(productsPage.getProductsHeading().isDisplayed());

    }
    @Test (priority = 20, description = "logoutWithLogoutButton")
    public void test2 () {
        String validUsername = excelReader.getStringData("Login",1,1);
        String validPassword = excelReader.getStringData("Login", 1,2);

        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(validPassword);
        loginPage.clickOnLoginButton();
        productsPage.getMenuButton().click();
        wdwait.until(ExpectedConditions.visibilityOf(productsPage.getLogoutTab()));
        productsPage.getLogoutTab().click();

        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        Assert.assertTrue(loginPage.getUsernameTextbox().isDisplayed());

    }

    @Test (priority = 30, description = "logout By Deleting Cookies")
    public void test3 () {
        String validUsername = excelReader.getStringData("Login",1,1);
        String validPassword = excelReader.getStringData("Login", 1,2);

        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(validPassword);
        loginPage.clickOnLoginButton();
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

    @Test (priority = 40, description = "loginWithCookies")
    public void test4 () {
        Cookie kolacic = new Cookie("session-username", "standard_user");
        driver.manage().addCookie(kolacic);
        driver.navigate().refresh();
    }

    @Test (priority = 50, description = "loginWithInvalidUsername")
    public void test5 () {
        for (int i = 0; i < excelReader.getLastRow("Login"); i++) {
            String invalidUsername = excelReader.getStringData("Login",i,3);
            String validPassword = excelReader.getStringData("Login", 1,2);
            loginPage.enterUsername(invalidUsername);
            loginPage.enterPassword(validPassword);
            loginPage.clickOnLoginButton();
            Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
            boolean check = true;
            try {
                productsPage.getCart().isDisplayed();
            } catch (Exception e) {
                check = false;
            }
            Assert.assertFalse(check);

            driver.navigate().refresh();    //ovo mora jer ne brise unos, vec posle klika ostaju, ili da se ide clear na username i password
        }

    }

    @Test (priority = 60, description = "addSpecidicItemToCart_andRemoveFromCart")
    public void test6 () throws InterruptedException {
        String validUsername = excelReader.getStringData("Login",1,1);
        String validPassword = excelReader.getStringData("Login", 1,2);

        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(validPassword);
        loginPage.clickOnLoginButton();

        scrollIntoView(productsPage.getBody());

        wdwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(className("inventory_item_name")));
        productsPage.selectLastSauceItem();

        Assert.assertEquals(sauceLabsOnesie_item_page.getProductTitle().getText(), "Sauce Labs Onesie");
        Assert.assertTrue(sauceLabsOnesie_item_page.getAddToCartButton().isDisplayed());
        Assert.assertEquals(sauceLabsOnesie_item_page.getCart().getText(), "");

        sauceLabsOnesie_item_page.clickOnAddToCart();

        Assert.assertEquals(sauceLabsOnesie_item_page.getCart().getText(), "1");

        sauceLabsOnesie_item_page.clickOnRemoveFromCart();
        Assert.assertEquals(sauceLabsOnesie_item_page.getCart().getText(), "");
        System.out.println(sauceLabsOnesie_item_page.getGetProductPrice().getAttribute("textContent"));

    }

    @Test (priority = 70, description = "removeElementsFromCart")
    public void test7 () {
        String validUsername = excelReader.getStringData("Login",1,1);
        String validPassword = excelReader.getStringData("Login",1,2);

        loginPage.enterUsername(validUsername);
        loginPage.enterPassword(validPassword);
        loginPage.clickOnLoginButton();
        scrollIntoView(productsPage.getBody());
        productsPage.getAddToCartBackpack().click();
        scrollIntoView(productsPage.getCart());
        productsPage.getCart().click();
        Assert.assertTrue(cartPage.getCartTitle().isDisplayed());
        Assert.assertTrue(cartPage.getCheckoutButton().isDisplayed());
        Assert.assertEquals(cartPage.getProductPrice().getText(),"$29.99");
        System.out.println(cartPage.getProductPrice().getText());
        cartPage.chooseNumerOfRemoveButtonToClick(1);
        boolean check;
        try {
            check = cartPage.getQuantityBox().isDisplayed();
        } catch (Exception e) {
            check = false;
        }
        Assert.assertFalse(check);

    }
}
