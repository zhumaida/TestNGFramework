package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SaucedemoHomePage;
import pages.SaucedemoLoginPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBase;



public class SaucedemoTests extends TestBase {

    @Test(groups = {"regression", "smoke", "saucedemo", "saucePositive", "sauceLogin"})
    public void sauceLoginPositive(){
       driver.get(ConfigReader.getProperty("saucedemoUrl"));

        SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();

        saucedemoLoginPage.saucedemoLogin("sauceStandardUser", "saucePassword");

        SaucedemoHomePage saucedemoHomePage = new SaucedemoHomePage();

        Assert.assertEquals(saucedemoHomePage.productsTitle.getText(), "Products");
    }

    @Test (priority = 1, groups = {"regression", "smoke", "saucedemo", "sauceNegative", "sauceLogin" })

    public void sauceLoginNegative(){
       driver.get(ConfigReader.getProperty("saucedemoUrl"));

       SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();

       saucedemoLoginPage.saucedemoLogin("sauceLockedUser", "saucePassword");

       String expectedMessage="Epic sadface: Sorry, this user has been locked out.";

        Assert.assertEquals(saucedemoLoginPage.lockedOutMessage.getText(), expectedMessage);

    }
    @Test (priority = 2, groups = {"regression", "saucedemo", "saucePositive", "productSorting"})
    public void saucePriceSortLowHigh(){

        driver.get(ConfigReader.getProperty("saucedemoUrl"));
        SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();
        saucedemoLoginPage.saucedemoLogin("sauceStandardUser", "saucePassword");

        SaucedemoHomePage saucedemoHomePage = new SaucedemoHomePage();
        Select sortSelect = new Select(saucedemoHomePage.productSortDropdown);
        sortSelect.selectByValue("lohi");


        for (int i=1 ; i<saucedemoHomePage.itemPrices.size(); i++){
            double itemPrice1 = Double.parseDouble(saucedemoHomePage.itemPrices.get(i-1).getText().substring(1));
            double itemPrice2 = Double.parseDouble(saucedemoHomePage.itemPrices.get(i).getText().substring(1));

            Assert.assertTrue(itemPrice2>=itemPrice1);
            System.out.println();





        }
    }
}
