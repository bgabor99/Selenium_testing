import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


class BeacPage extends PageBase {

    private By headTitleBy = By.className("page-head-title");
    private By sortDropDownSelectBy = By.xpath("//*/select");
    private By dragPrizeFilterBy = By.xpath("//*[@id='no-ui-slider-widget-system_price']/div/div[3]/div/div[1]");
    private By dropPrizeFilterBy = By.xpath("//*[@id='no-ui-slider-widget-system_price']/div/div[2]/div/div[1]");
    private By showNumberOfProductsDivBy = By.xpath("(//*/div[contains(text(),'Showing')])[1]");
    
    public BeacPage(WebDriver driver) {
        super(driver);
    }

    public String getHeadTitleText() {
        return this.waitAndReturnElement(headTitleBy).getText();
    }

    public BeacPage clickSortDropDown() {
        WebElement dropDown = driver.findElement(sortDropDownSelectBy);
        Select se = new Select(dropDown);
        se.selectByValue("https://elteshop.com/beac-181?sort=p.price&order=DESC&page=1");
        return new BeacPage(this.driver);
    }

    public String getSortDropDownValue() {
        WebElement dropDown = driver.findElement(sortDropDownSelectBy);
        Select se = new Select(dropDown);
        WebElement option = se.getFirstSelectedOption();
        String actItem = option.getText();
        return actItem;
    }

    public BeacPage dragAndDropPrize() {
        WebElement dragElement = driver.findElement(dragPrizeFilterBy);
        WebElement dropElement = driver.findElement(dropPrizeFilterBy);
        Actions builder = new Actions(driver);
        Action dragAndDrop = builder.clickAndHold(dragElement).moveToElement(dropElement).release(dropElement).build();
        dragAndDrop.perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new BeacPage(this.driver);
    }

    public String getShowNumberOfProductsDivText() {
        return this.waitAndReturnElement(showNumberOfProductsDivBy).getText();
    }

}
