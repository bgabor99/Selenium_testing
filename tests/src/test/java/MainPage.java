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


class MainPage extends PageBase {

    private By footerBy = By.className("d-print-none");
    private By searchBarTogglerBy = By.xpath("//footer[@class='d-print-none']");
    private By searchBarBy = By.id("filter_keyword");
    private By loginPageBtnBy = By.xpath("//*[@id='section-header']/header/div/nav/div[1]/div[4]/div[1]/ul/li/a");
    
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
}
