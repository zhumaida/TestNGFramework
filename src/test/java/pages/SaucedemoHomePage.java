package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class SaucedemoHomePage {

    WebDriver driver;

    public SaucedemoHomePage(){

        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);

    }
    @FindBy(xpath = "//span[@class='title']")
    public WebElement productsTitle;

    @FindBy(xpath = "//select[@class='product_sort_container']")
    public WebElement productSortDropdown;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    public List<WebElement> itemPrices;

}
