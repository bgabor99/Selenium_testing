import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;


class SearchResultPage extends PageBase {

    public SearchResultPage(WebDriver driver) {
        super(driver);
        //waitAndReturnElement(bodyLocator);
    }

    public SpecificProductPage clickHyperLinkByText(String textName){
        By locator = By.xpath("//h2/a[contains(text(),'" + textName + "')]//ancestor::a");
        waitAndReturnElement(locator).click();
        return new SpecificProductPage(this.driver);
    }
}
