import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.Cookie;
import java.net.URL;
import java.net.MalformedURLException;


public class SeleniumElteShopTest {
    public WebDriver driver;

    @Before
    public void setup()  throws MalformedURLException  {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
    }

    @Test
    public void testMainPageFooter() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.getFooterText().contains("Partners"));
        Assert.assertTrue(mainPage.getFooterText().contains("SHOP"));
    }

    @Test
    public void testMainPageTitle() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.driver.getTitle().contains("ELTE Shop"));
    }
    
    @Test
    public void testSearchFound() {
        MainPage mainPage = new MainPage(this.driver);
        SearchResultPage searchResultPage = mainPage.search("polo");
        String bodyText = searchResultPage.getBodyText();
        Assert.assertTrue(bodyText.contains("ADD TO CART"));
        Assert.assertTrue(bodyText.contains("BEAC"));
    }

    @Test
    public void testSearchNotFound() {
        MainPage mainPage = new MainPage(this.driver);
        SearchResultPage searchResultPage = mainPage.search("virag");
        String bodyText = searchResultPage.getBodyText();
        Assert.assertTrue(bodyText.contains("Not match for this search criteria"));
    }
    
    @Test
    public void testSearchQueriesFound() {
        String[] searchQueries={"sapka","bogre"};  
        for(String searchQuery : searchQueries) {
            //System.out.println("searchQuery: " + searchQuery);  
            MainPage mainPage = new MainPage(this.driver);
            SearchResultPage searchResultPage = mainPage.search(searchQuery);
            String bodyText = searchResultPage.getBodyText();
            Assert.assertTrue(bodyText.contains("ADD TO CART"));
        }  
    }

    @Test
    public void testSearchQueriesNotFound() {
        String[] searchQueries={"auto","motor"};  
        for(String searchQuery : searchQueries) {
            MainPage mainPage = new MainPage(this.driver);
            SearchResultPage searchResultPage = mainPage.search(searchQuery);
            String bodyText = searchResultPage.getBodyText();
            Assert.assertTrue(bodyText.contains("Not match for this search criteria"));
        }  
    }

    @Test
    public void testSearchFoundAndClickOne() {
        MainPage mainPage = new MainPage(this.driver);
        SearchResultPage searchResultPage = mainPage.search("jegyzetcsipesz");
        String bodyText = searchResultPage.getBodyText();
        Assert.assertTrue(bodyText.contains("ADD TO CART"));
        Assert.assertTrue(bodyText.contains("fa"));
        SpecificProductPage specificProductPage = searchResultPage.clickHyperLinkByText("fa jegyzetcsipesz");
        bodyText = specificProductPage.getBodyText();
        Assert.assertTrue(bodyText.contains("Manufacturer"));
    }

    @Test
    public void testSearchPageTitle() {
        MainPage mainPage = new MainPage(this.driver);
        SearchResultPage searchResultPage = mainPage.search("jegyzetcsipesz");
        Assert.assertTrue(searchResultPage.driver.getTitle().contains("Search"));
    }

    @Test
    public void testSpecificProductPageTitle() {
        MainPage mainPage = new MainPage(this.driver);
        SearchResultPage searchResultPage = mainPage.search("jegyzetcsipesz");
        SpecificProductPage specificProductPage = searchResultPage.clickHyperLinkByText("fa jegyzetcsipesz");
        Assert.assertTrue(specificProductPage.driver.getTitle().contains("fa jegyzetcsipesz"));
    }

    @Test
    public void testLoginPageHeadTitle() {
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.clickLoginPageBtn();
        Assert.assertTrue(loginPage.getHeadTitleText().contains("LOGIN"));
    }

    @Test
    public void testLoginPageFillAndLoggedAccountPageHeadTitle() {
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.clickLoginPageBtn();
        LoggedInAccountPage loggedInAccountPage = loginPage.login();
        System.out.println(loggedInAccountPage.getHeadTitleText());
        Assert.assertTrue(loggedInAccountPage.getHeadTitleText().contains("MY ACCOUNT"));
    }

    @Test
    public void testNewsLetterPageTitle() {
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.clickLoginPageBtn();
        LoggedInAccountPage loggedInAccountPage = loginPage.login();
        NewsLetterPage newsLetterPage = loggedInAccountPage.getNewsLetterPageLinkClick();
        Assert.assertTrue(newsLetterPage.getHeadTitleText().contains("NEWSLETTER SUBSCRIPTION"));
    }

    @Test
    public void testNewsLetterPageSubscribeRadioBtn() {
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.clickLoginPageBtn();
        LoggedInAccountPage loggedInAccountPage = loginPage.login();
        NewsLetterPage newsLetterPage = loggedInAccountPage.getNewsLetterPageLinkClick();
        loggedInAccountPage = newsLetterPage.subscribe();
        Assert.assertTrue(loggedInAccountPage.getSuccesAlertText().contains("Success"));
    }

    @Test
    public void testNewsLetterPageUnSubscribeRadioBtn() {
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.clickLoginPageBtn();
        LoggedInAccountPage loggedInAccountPage = loginPage.login();
        NewsLetterPage newsLetterPage = loggedInAccountPage.getNewsLetterPageLinkClick();
        loggedInAccountPage = newsLetterPage.unsubscribe();
        Assert.assertTrue(loggedInAccountPage.getSuccesAlertText().contains("Success"));
    }

    @Test
    public void testEditAccountPageFormSend() {
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.clickLoginPageBtn();
        LoggedInAccountPage loggedInAccountPage = loginPage.login();
        EditAccountPage editAccountPage= loggedInAccountPage.getEditAccountPageLinkClick();
        loggedInAccountPage = editAccountPage.editFirstName("TestName");
        Assert.assertTrue(loggedInAccountPage.getSuccesAlertText().contains("Success"));
    }

    @Test
    public void testHoverAccountMenuAndLogOff() {
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.clickLoginPageBtn();
        LoggedInAccountPage loggedInAccountPage = loginPage.login();
        loginPage = loggedInAccountPage.hoverAccountMenuAndLogOff();
        Assert.assertTrue(loginPage.getHeadTitleText().contains("LOGIN"));
    }

    @Test
    public void testHistoryPreviousPage() {
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.clickLoginPageBtn();
        Assert.assertTrue(loginPage.getHeadTitleText().contains("LOGIN"));
        driver.navigate().back();
        Assert.assertTrue(mainPage.driver.getTitle().contains("ELTE Shop"));
    }

    @Test
    public void testAvoidCookiePopup() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.getCookieDivText().contains("Cookie"));
        driver.manage().addCookie(new Cookie("auroraNanobarAccepted","1"));
        driver.navigate().refresh();
        Assert.assertTrue(mainPage.getCookieDivText().contains("TimeoutException"));
    }


    @Test
    public void testBeacPageTitle() {
        MainPage mainPage = new MainPage(this.driver);
        BeacPage beacPage = mainPage.clickBeacLink();
        Assert.assertTrue(beacPage.getHeadTitleText().contains("ELTE BEAC"));
    }

    @Test
    public void testBeacPageSortDropDownTitle() {
        MainPage mainPage = new MainPage(this.driver);
        BeacPage beacPage = mainPage.clickBeacLink();
        Assert.assertTrue(beacPage.getSortDropDownValue().contains("Low < High"));
        beacPage = beacPage.clickSortDropDown();
        Assert.assertTrue(beacPage.getSortDropDownValue().contains("High > Low"));
    }

    @Test
    public void testBeacPageDragAndDropPize() {
        MainPage mainPage = new MainPage(this.driver);
        BeacPage beacPage = mainPage.clickBeacLink();
        beacPage = beacPage.dragAndDropPrize();
        Assert.assertTrue(beacPage.getShowNumberOfProductsDivText().contains("Showing 1 to 1"));
    }

    @Test
    public void testKeepBrowserOpen() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("detach", true);
        try {
            driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
