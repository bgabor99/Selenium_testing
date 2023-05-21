import java.util.concurrent.TimeUnit;

import javax.swing.Action;

import org.apache.bcel.generic.ACONST_NULL;
import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


class LoggedInAccountPage extends PageBase {

    private By headTitleBy = By.className("page-head-title");
    private By newLetterLinkBy = By.xpath("//*/a[contains(text(),'Subscribe / unsubscribe to newsletter')]");
    private By editAccountLink = By.xpath("//*/a[contains(text(), 'Edit your account')]");
    private By subscribeSuccesAlertBy = By.xpath("//*[@id='mm-0']/div[2]/main/div[2]/div/section/div/div[2]/div[1]");
    private WebElement accountMenu;
    private By accountMenuBy = By.xpath("//*/span[contains(text(),'Welcome')]//ancestor::a");
    private WebElement btnLogOff;
    private By btnLogOffBy = By.xpath("//*[@id='section-header']/header/div/nav/div[1]/div[4]/div[1]/ul/li/ul/li[4]/a");


    public LoggedInAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getHeadTitleText() {
        return this.waitAndReturnElement(headTitleBy).getText();
    }

    public NewsLetterPage getNewsLetterPageLinkClick() {
        this.waitAndReturnElement(newLetterLinkBy).click();
        return new NewsLetterPage(driver);
    }

    public EditAccountPage getEditAccountPageLinkClick() {
        this.waitAndReturnElement(editAccountLink).click();
        return new EditAccountPage(driver);
    }

    public String getSuccesAlertText() {
        return this.waitAndReturnElement(subscribeSuccesAlertBy).getText();
    }

    public LoginPage hoverAccountMenuAndLogOff() {
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        Actions actions = new Actions(driver);
        accountMenu = driver.findElement(accountMenuBy);
        actions.moveToElement(accountMenu).build().perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        btnLogOff = driver.findElement(btnLogOffBy);
        actions.click(btnLogOff).perform();
        return new LoginPage(driver);
    }

}
