import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;


class SpecificProductPage extends PageBase {

    public SpecificProductPage(WebDriver driver) {
        super(driver);
    }

    
    public void clickHyperLinkByText(String textName){
        By locator = By.xpath("//h2/a[contains(text(),'" + textName + "')]//ancestor::a");
        waitAndReturnElement(locator).click();
    }
}
