package utilities;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.UUID;

public class BrowserUtils {

    /**
     * This method generates a random email using UUID
     * EX: .getRandomEmail() -> returns -> username-1234kjnknkj-1234kjbhj-mbfkgb2@gmail.com
     */

    public static String getRandomEmail(){
        UUID uuid = UUID.randomUUID();
        return "username"+uuid+"@gmail.com";
    }
    /**
     * This methods creates a Select object and selects an option by value
     * @param target
     * @param value
     */
    public static void selectOptionByValue(WebElement target, String value){
        Select select = new Select(target);
        select.selectByValue(value);
    }

    public static void waitForElementToBeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 15);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


}
