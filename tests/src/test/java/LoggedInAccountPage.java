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


class LoggedInAccountPage extends PageBase {

    private By headTitleBy = By.className("page-head-title");
    private By newLetterLinkBy = By.xpath("//*/a[contains(text(),'Subscribe / unsubscribe to newsletter')]");
    private By editAccountLink = By.xpath("//*/a[contains(text(), 'Edit your account')]");
    private By subscribeSuccesAlertBy = By.xpath("//*[@id='mm-0']/div[2]/main/div[2]/div/section/div/div[2]/div[1]");

    public LoggedInAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getHeadTitleText() {
        return this.waitAndReturnElement(headTitleBy).getText();
    }

    public NewsLetterPage getNewsLetterPageLinkClick() {
        this.waitAndReturnElement(newLetterLinkBy).click();;
        return new NewsLetterPage(driver);
    }

    public EditAccountPage getEditAccountPageLinkClick() {
        this.waitAndReturnElement(editAccountLink).click();;
        return new EditAccountPage(driver);
    }

    public String getSuccesAlertText() {
        return this.waitAndReturnElement(subscribeSuccesAlertBy).getText();
    }

}
