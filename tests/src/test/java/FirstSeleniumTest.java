import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import java.util.*;  

import java.net.URL;
import java.net.MalformedURLException;


public class FirstSeleniumTest {
    public WebDriver driver;
    
    @Before
    public void setup()  throws MalformedURLException  {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
    }

    @Test
    public void testMainPageFooter() {
        System.out.println("---- testMainPageFooter ----");
        MainPage mainPage = new MainPage(this.driver);
        //System.out.println(mainPage.getFooterText());
        Assert.assertTrue(mainPage.getFooterText().contains("Partners"));
        Assert.assertTrue(mainPage.getFooterText().contains("SHOP"));
    }
    
    @Test
    public void testSearchFound() {
        System.out.println("---- testSearchFound ----");
        MainPage mainPage = new MainPage(this.driver);
        //Assert.assertTrue(mainPage.getFooterText().contains("SHOP"));

        SearchResultPage searchResultPage = mainPage.search("polo");
        String bodyText = searchResultPage.getBodyText();
        //System.out.println(bodyText);
        Assert.assertTrue(bodyText.contains("ADD TO CART"));
        Assert.assertTrue(bodyText.contains("BEAC"));
    }

    @Test
    public void testSearchNotFound() {
        System.out.println("---- testSearchNotFound ----");
        MainPage mainPage = new MainPage(this.driver);
        //Assert.assertTrue(mainPage.getFooterText().contains("SHOP"));

        SearchResultPage searchResultPage = mainPage.search("virag");
        String bodyText = searchResultPage.getBodyText();
        //System.out.println(bodyText);
        Assert.assertTrue(bodyText.contains("Not match for this search criteria"));
    }
    
    @Test
    public void testSearchQueriesFound() {
        System.out.println("---- testSearchQueries ----");
        String[] searchQueries={"sapka","bogre"};  
        for(String searchQuery : searchQueries) {
            //System.out.println("searchQuery: " + searchQuery);  
            MainPage mainPage = new MainPage(this.driver);
            SearchResultPage searchResultPage = mainPage.search(searchQuery);
            String bodyText = searchResultPage.getBodyText();
            //System.out.println(bodyText);
            //System.out.println(" -------- ");
            Assert.assertTrue(bodyText.contains("ADD TO CART"));
        }  
    }

    @Test
    public void testSearchQueriesNotFound() {
        System.out.println("---- testSearchQueries ----");
        String[] searchQueries={"auto","motor"};  
        for(String searchQuery : searchQueries) {
            //System.out.println("searchQuery: " + searchQuery);  
            MainPage mainPage = new MainPage(this.driver);
            SearchResultPage searchResultPage = mainPage.search(searchQuery);
            String bodyText = searchResultPage.getBodyText();
            //System.out.println(bodyText);
            //System.out.println(" -------- ");
            Assert.assertTrue(bodyText.contains("Not match for this search criteria"));
        }  
    }

    @Test
    public void testSearchFoundAndClickOne() {
        System.out.println("---- testSearchFoundAndClickOne ----");

        MainPage mainPage = new MainPage(this.driver);
        //Assert.assertTrue(mainPage.getFooterText().contains("SHOP"));

        SearchResultPage searchResultPage = mainPage.search("fa jegyzetcsipesz");
        String bodyText = searchResultPage.getBodyText();
        //System.out.println(bodyText);
        Assert.assertTrue(bodyText.contains("ADD TO CART"));
        Assert.assertTrue(bodyText.contains("fa"));

        SpecificProductPage specificProductPage = searchResultPage.clickHyperLinkByText("fa jegyzetcsipesz");
        bodyText = specificProductPage.getBodyText();
        //System.out.println(bodyText);
        Assert.assertTrue(bodyText.contains("Manufacturer"));
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
