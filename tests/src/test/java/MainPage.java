import org.openqa.selenium.WebDriver;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;


class MainPage extends PageBase {

    private ImmutablePair<String, String> cookieAcceptValueAndKeyPair = ImmutablePair.of("auroraNanobarAccepted", "1");
    private By footerBy = By.className("d-print-none");
    private By searchBarTogglerBy = By.xpath("//footer[@class='d-print-none']");
    private By searchBarBy = By.id("filter_keyword");
    private By loginPageBtnBy = By.xpath("//*[@id='section-header']/header/div/nav/div[1]/div[4]/div[1]/ul/li/a");
    private By cookieDivBy = By.xpath("//*/div[contains(text(),'cookie')]");
    private By beacLinkBy = By.xpath("//*[@id='cat_181']/a");
    
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://elteshop.com/");
    }

    public String getFooterText() {
        return this.waitAndReturnElement(footerBy).getText();
    }
    
    public SearchResultPage search(String searchQuery) {
        this.waitAndReturnElement(searchBarTogglerBy).click();
        this.waitAndReturnElement(searchBarBy).sendKeys(searchQuery + "\n");
        return new SearchResultPage(this.driver);
    }

    public LoginPage clickLoginPageBtn() {
        this.waitAndReturnElement(loginPageBtnBy).click();
        return new LoginPage(this.driver);
    }

    public BeacPage clickBeacLink() {
        this.waitAndReturnElement(beacLinkBy).click();
        return new BeacPage(this.driver);
    }

    public Cookie getAcceptCookie(){
        return new Cookie(cookieAcceptValueAndKeyPair.left, cookieAcceptValueAndKeyPair.right);
    }

    public String getCookieDivText() {
        try {
            return this.waitAndReturnElement(cookieDivBy).getText();
        } catch (Exception e) {
            return e.toString();
        }
    }
}
