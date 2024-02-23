package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.MagentoCreateAccountPage;
import pages.MagentoMainPage;
import pages.MagentoSignInPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;


public class MagentoTests extends TestBase {

    String gFirstName;
    String gLastNme;
    String gEmail;
    String gPassword;



    // DataProvider runs once for every set of data
    // If we're using the same dataprovider in a different test method or class, we need to use all its values;
    @DataProvider(name = "createAccountData")
    public static Object[][] testData(){
        return new Object[][]{
                {"Harsh", "Doe", BrowserUtils.getRandomEmail(), "qQ11111!"},
                {"Mary", "Lee", BrowserUtils.getRandomEmail(), "qQ11111!"},
        };
    }

    @Test(groups = {"regression", "smoke", "magento", "magentoPositive", "createAccount"}, dataProvider = "createAccountData")
    public void magentoCreateAccount(
            String firstNameData,
            String lastNameData,
            String emailData,
            String passwordData
    ) {
        this.gFirstName = firstNameData;
        this.gLastNme = lastNameData;
        this.gEmail = emailData;
        this.gPassword = passwordData;
        driver.get(ConfigReader.getProperty("magentoUrl"));
        MagentoMainPage magentoMainPage = new MagentoMainPage();
        magentoMainPage.createAccountLink.click();
        MagentoCreateAccountPage magentoCreateAccountPage = new MagentoCreateAccountPage();
        magentoCreateAccountPage.firstName.sendKeys(firstNameData);
        magentoCreateAccountPage.lastName.sendKeys(lastNameData);

        magentoCreateAccountPage.email.sendKeys(emailData);
        System.out.println(emailData);


        magentoCreateAccountPage.password.sendKeys(passwordData);
        magentoCreateAccountPage.confirmPassword.sendKeys(passwordData);
        magentoCreateAccountPage.createAnAccountBtn.click();

        if(!firstNameData.equals("#Mary-Beth")){
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.titleIs("My Account"));
            Assert.assertEquals(driver.getTitle(), "My Account");
        } else {
            Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
        }
    }

    @Test(groups = {"regression", "smoke", "magento", "magentoPositive", "signIn"}, dependsOnMethods = {"magentoCreateAccount"})
    public void magentoSignIn(){
        driver.get(ConfigReader.getProperty("magentoUrl"));
        MagentoMainPage magentoMainPage = new MagentoMainPage();
        magentoMainPage.signInLink.click();
        MagentoSignInPage magentoSignInPage = new MagentoSignInPage();
        magentoSignInPage.signInEmail.sendKeys(gEmail);
        magentoSignInPage.signInPassword.sendKeys(gPassword);
        magentoSignInPage.signInBtn.click();
        String expectedMsg = "Welcome, "+gFirstName+" "+gLastNme+"!";

        BrowserUtils.waitForTextToBePresent(magentoMainPage.welcomeMsg, expectedMsg);

        Assert.assertEquals(magentoMainPage.welcomeMsg.getText(),expectedMsg);




    }

}







