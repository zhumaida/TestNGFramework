package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.RetryFailedTests;
import utilities.TestBase;

public class FrameworkTest extends TestBase {


    @Test(groups = {"regression", "smoke", "frameworkTest"}, retryAnalyzer = RetryFailedTests.class)
    public void verifyGoogleHomepage(){
        driver.get("https://www.google.com/");
        Assert.assertEquals(driver.getTitle(),"Google");

    }
}
