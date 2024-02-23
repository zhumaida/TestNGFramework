package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;

public class SaucedemoLoginPage {

    WebDriver driver; //create webdriver is step 1

    public SaucedemoLoginPage(){     //create constructor of this page
        driver = Driver.getDriver();    //initialize the driver
        PageFactory.initElements(driver, this);


    }

    @FindBy(id = "user-name")
    public WebElement usernameInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(id = "login-button")
    public WebElement loginBtn;

    @FindBy(xpath = "//h3[contains(text(), 'locked out')]")
    public WebElement lockedOutMessage;

    /**
     *This method logs in to Saucedemo app using the username and password keys
     * @param usernameKey - the key to the username in Configuration.properties file.
     * @param passwordKey - the key to the password in Configuration.properties.file
     */

    public void saucedemoLogin(String usernameKey, String passwordKey){
        usernameInput.sendKeys(ConfigReader.getProperty(usernameKey)); //usernameKey=sauceStandardUser
        passwordInput.sendKeys(ConfigReader.getProperty(passwordKey));
        loginBtn.click();
    }

}
