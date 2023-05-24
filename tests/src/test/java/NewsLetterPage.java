import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;


class NewsLetterPage extends PageBase {

    private By headTitleBy = By.className("page-head-title");
    //private By subscribeRadioBtn = By.id("newsletter-yes");
    private By subscribeRadioBtn = By.xpath("//*[@id='newsletter']/div[1]/div/div[1]/label");
    private By unSubscribeRadioBtn = By.xpath("//*[@id='newsletter']/div[1]/div/div[2]/label");
    private By submitSubscribeBtn = By.xpath("//button[contains(text(), 'Continue')]");
    
    public NewsLetterPage(WebDriver driver) {
        super(driver);
    }

    public String getHeadTitleText() {
        return this.waitAndReturnElement(headTitleBy).getText();
    }

    public LoggedInAccountPage subscribe() {
        waitAndReturnElement(subscribeRadioBtn).click();
        waitAndReturnElement(submitSubscribeBtn).click();
        return new LoggedInAccountPage(driver);
    }

    public LoggedInAccountPage unsubscribe() {
        waitAndReturnElement(unSubscribeRadioBtn).click();
        waitAndReturnElement(submitSubscribeBtn).click();
        return new LoggedInAccountPage(driver);
    }

}
