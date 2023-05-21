import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;


class LoginPage extends PageBase {

    private String email = "testselenium1@temp.com";
    private String password = "testselenium1";
    private By headTitleBy = By.className("page-head-center-title");
    //private By emailInputBy = By.id("email_login");
    private By emailInputBy = By.xpath("//*/label[contains(text(),'E-mail address')]//preceding-sibling::input");
    //private By passwordInputBy = By.className("password_login");
    private By passwordInputBy = By.xpath("//*/label[contains(text(),'Password')]//preceding-sibling::input");
    private By loginBtnBy = By.xpath("//*/span[contains(text(),'Login')]//ancestor::button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String getHeadTitleText() {
        return this.waitAndReturnElement(headTitleBy).getText();
    }

    public LoggedInAccountPage login() {
        this.waitAndReturnElement(emailInputBy).sendKeys(email);
        this.waitAndReturnElement(passwordInputBy).sendKeys(password);
        this.waitAndReturnElement(loginBtnBy).click();
        return new LoggedInAccountPage(this.driver);
    }

}
