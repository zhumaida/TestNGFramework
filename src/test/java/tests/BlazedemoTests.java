package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.util.concurrent.TimeUnit;

public class BlazedemoTests extends TestBase {

       String fromCity="Boston";
       String toCity="New York";

       @Test(groups = {"regression", "smoke", "blazedemo", "blazePositive", "flights"})
    public void flightCities(){
        driver.get(ConfigReader.getProperty("blazedemoUrl"));
        Select departureCityDropdown = new Select(driver.findElement(By.name("fromPort")));  // select object created for dropdown
        departureCityDropdown.selectByValue("Boston");

        Select destinationCityDropdown = new Select(driver.findElement(By.name("toPort"))); //variable can be provided for Select
        destinationCityDropdown.selectByValue("New York");

           WebElement findFlightsBtn = driver.findElement(By.xpath("//input[@value='Find Flights']"));
           findFlightsBtn.click();

           WebElement actualFlightsMessage = driver.findElement(By.xpath("//h3[contains(text(),'Flights from')]"));
           String expectedFlightsMessage = "Flights from Boston to New York:";

           Assert.assertEquals(actualFlightsMessage.getText(),expectedFlightsMessage);

    }

    @Test(groups = {"regression", "blazedemo", "blazePositive", "flights"})
    public void flightInfo(){
        driver.get(ConfigReader.getProperty("blazedemoUrl"));

        WebElement departureCity = driver.findElement(By.name("fromPort"));
        BrowserUtils.selectOptionByValue(departureCity, fromCity);

        WebElement destinationCity = driver.findElement(By.name("toPort"));
        BrowserUtils.selectOptionByValue(destinationCity,fromCity);


        WebElement findFlightBtn = driver.findElement(By.xpath("//input[@value='Find Flights']"));


        findFlightBtn.click();

        WebElement chooseFlightBtn = driver.findElement(By.xpath("//tr[2]/td[1]/input"));
        String expectedFlightNum = driver.findElement(By.xpath("//tr[2]/td[2]")).getText();
        String expectedAirline = driver.findElement(By.xpath("//tr[2]/td[3]")).getText();
        String expectedPrice = driver.findElement(By.xpath("//tr[2]/td[6]")).getText();

        chooseFlightBtn.click();
        String actualAirline = driver.findElement(By.xpath("//p[contains(text(),'Airline')]")).getText();
        String actualFlightNum = driver.findElement(By.xpath("//p[contains(text(),'Flight Number')]")).getText();
        String actualPrice = driver.findElement(By.xpath("//p[contains(text(),'Price')]")).getText();


        //Assert.assertEquals(actualAirline.substring(actualAirline.indexOf(":")+2)+" Airlines",expectedAirline);
        //Assert.assertEquals(actualFlightNum.substring(actualFlightNum.indexOf(":")+2),expectedFlightNum);
        //Assert.assertEquals(actualPrice.substring(actualPrice.indexOf(":")+2),expectedPrice);

        SoftAssert softAssert=new SoftAssert();

        softAssert.assertEquals(actualAirline.substring(actualAirline.indexOf(":")+2)+" Airlines",expectedAirline);
        softAssert.assertEquals(actualFlightNum.substring(actualFlightNum.indexOf(":")+2),expectedFlightNum);
        softAssert.assertEquals(actualPrice.substring(actualPrice.indexOf(":")+2),expectedPrice);
        softAssert.assertAll();

    }
}

