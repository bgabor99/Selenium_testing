import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


class EditAccountPage extends PageBase {

    private By headTitleBy = By.className("page-head-title");
    //private By subscribeRadioBtn = By.id("newsletter-yes");
    private By firstNameInput = By.name("firstname");
    private By continueBtn = By.xpath("//*/button[contains(text(), 'Continue')]");
    
    public EditAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getHeadTitleText() {
        return this.waitAndReturnElement(headTitleBy).getText();
    }

    public LoggedInAccountPage editFirstName(String input) {
        waitAndReturnElement(firstNameInput).clear();
        waitAndReturnElement(firstNameInput).sendKeys(input);
        waitAndReturnElement(continueBtn).click();
        return new LoggedInAccountPage(driver);
    }


}
