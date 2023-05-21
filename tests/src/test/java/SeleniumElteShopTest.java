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
        System.out.println("---- testMainPageFooter ----");
        MainPage mainPage = new MainPage(this.driver);
        //System.out.println(mainPage.getFooterText());
        Assert.assertTrue(mainPage.getFooterText().contains("Partners"));
        Assert.assertTrue(mainPage.getFooterText().contains("SHOP"));
    }

    @Test
    public void testMainPageTitle() {
        System.out.println("---- testMainPageTitle ----");
        MainPage mainPage = new MainPage(this.driver);
        //System.out.println(mainPage.driver.getTitle());
        Assert.assertTrue(mainPage.driver.getTitle().contains("ELTE Shop"));
    }
    
    @Test
    public void testSearchFound() {
        System.out.println("---- testSearchFound ----");
        MainPage mainPage = new MainPage(this.driver);

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
            Assert.assertTrue(bodyText.contains("Not match for this search criteria"));
        }  
    }

    @Test
    public void testSearchFoundAndClickOne() {
        System.out.println("---- testSearchFoundAndClickOne ----");

        MainPage mainPage = new MainPage(this.driver);
        //Assert.assertTrue(mainPage.getFooterText().contains("SHOP"));

        SearchResultPage searchResultPage = mainPage.search("jegyzetcsipesz");
        String bodyText = searchResultPage.getBodyText();
        //System.out.println(bodyText);
        Assert.assertTrue(bodyText.contains("ADD TO CART"));
        Assert.assertTrue(bodyText.contains("fa"));

        SpecificProductPage specificProductPage = searchResultPage.clickHyperLinkByText("fa jegyzetcsipesz");
        bodyText = specificProductPage.getBodyText();
        //System.out.println(bodyText);
        Assert.assertTrue(bodyText.contains("Manufacturer"));
    }

    @Test
    public void testSearchPageTitle() {
        System.out.println("---- testSearchFoundAndClickOne ----");
        MainPage mainPage = new MainPage(this.driver);
        SearchResultPage searchResultPage = mainPage.search("jegyzetcsipesz");
        //System.out.println(specificProductPage.driver.getTitle());
        Assert.assertTrue(searchResultPage.driver.getTitle().contains("Search"));
    }

    @Test
    public void testSpecificProductPageTitle() {
        System.out.println("---- testSpecificProductPageTitle ----");
        MainPage mainPage = new MainPage(this.driver);
        SearchResultPage searchResultPage = mainPage.search("jegyzetcsipesz");
        SpecificProductPage specificProductPage = searchResultPage.clickHyperLinkByText("fa jegyzetcsipesz");
        //System.out.println(specificProductPage.driver.getTitle());
        Assert.assertTrue(specificProductPage.driver.getTitle().contains("fa jegyzetcsipesz"));
    }

    @Test
    public void testLoginPageHeadTitle() {
        System.out.println("---- testLoginPageHeadTitle ----");
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.clickLoginPageBtn();
        //System.out.println(loginPage.getHeadTitleText());
        Assert.assertTrue(loginPage.getHeadTitleText().contains("LOGIN"));
    }

    @Test
    public void testLoginPageFillAndLoggedAccountPageHeadTitle() {
        System.out.println("---- testLoginPageFill ----");
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.clickLoginPageBtn();
        LoggedInAccountPage loggedInAccountPage = loginPage.login();
        System.out.println(loggedInAccountPage.getHeadTitleText());
        Assert.assertTrue(loggedInAccountPage.getHeadTitleText().contains("MY ACCOUNT"));
    }

    @Test
    public void testNewsLetterPageTitle() {
        System.out.println("---- testNewsLetterPageTitle ----");
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.clickLoginPageBtn();
        LoggedInAccountPage loggedInAccountPage = loginPage.login();
        NewsLetterPage newsLetterPage = loggedInAccountPage.getNewsLetterPageLinkClick();
        //System.out.println(newsLetterPage.getHeadTitleText());
        Assert.assertTrue(newsLetterPage.getHeadTitleText().contains("NEWSLETTER SUBSCRIPTION"));
    }

    @Test
    public void testNewsLetterPageSubscribeRadioBtn() {
        System.out.println("---- testNewsLetterPageSubscribeRadioBtn ----");
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.clickLoginPageBtn();
        LoggedInAccountPage loggedInAccountPage = loginPage.login();
        NewsLetterPage newsLetterPage = loggedInAccountPage.getNewsLetterPageLinkClick();
        loggedInAccountPage = newsLetterPage.subscribe();
        Assert.assertTrue(loggedInAccountPage.getSuccesAlertText().contains("Success"));
    }

    @Test
    public void testNewsLetterPageUnSubscribeRadioBtn() {
        System.out.println("---- testNewsLetterPageUnSubscribeRadioBtn ----");
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.clickLoginPageBtn();
        LoggedInAccountPage loggedInAccountPage = loginPage.login();
        NewsLetterPage newsLetterPage = loggedInAccountPage.getNewsLetterPageLinkClick();
        loggedInAccountPage = newsLetterPage.unsubscribe();
        Assert.assertTrue(loggedInAccountPage.getSuccesAlertText().contains("Success"));
    }

    @Test
    public void testEditAccountPageFormSend() {
        System.out.println("---- testEditAccountFormSend ----");
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.clickLoginPageBtn();
        LoggedInAccountPage loggedInAccountPage = loginPage.login();
        EditAccountPage editAccountPage= loggedInAccountPage.getEditAccountPageLinkClick();
        loggedInAccountPage = editAccountPage.editFirstName("TestName");
        Assert.assertTrue(loggedInAccountPage.getSuccesAlertText().contains("Success"));
    }

    @Test
    public void testHoverAccountMenuAndLogOff() {
        System.out.println("---- testHoverAccountMenuAndLogOff ----");
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.clickLoginPageBtn();
        LoggedInAccountPage loggedInAccountPage = loginPage.login();
        loginPage = loggedInAccountPage.hoverAccountMenuAndLogOff();
        Assert.assertTrue(loginPage.getHeadTitleText().contains("LOGIN"));
    }

    @Test
    public void testHistoryPreviousPage() {
        System.out.println("---- testHistoryPreviousPage ----");
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.clickLoginPageBtn();
        Assert.assertTrue(loginPage.getHeadTitleText().contains("LOGIN"));
        driver.navigate().back();
        Assert.assertTrue(mainPage.driver.getTitle().contains("ELTE Shop"));
    }

    @Test
    public void testAvoidCookiePopup() {
        System.out.println("---- testAvoidCookiePopup ----");
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.getCookieDivText().contains("Cookie"));
        driver.manage().addCookie(new Cookie("auroraNanobarAccepted","1"));
        driver.navigate().refresh();
        Assert.assertTrue(mainPage.getCookieDivText().contains("TimeoutException"));
    }


    @Test
    public void testBeacPageTitle() {
        System.out.println("---- testBeacPageTitle ----");
        MainPage mainPage = new MainPage(this.driver);
        BeacPage beacPage = mainPage.clickBeacLink();
        Assert.assertTrue(beacPage.getHeadTitleText().contains("ELTE BEAC"));
    }

    @Test
    public void testBeacPageSortDropDownTitle() {
        System.out.println("---- testBeacPageSortDropDownTitle ----");
        MainPage mainPage = new MainPage(this.driver);
        BeacPage beacPage = mainPage.clickBeacLink();
        Assert.assertTrue(beacPage.getSortDropDownValue().contains("Low < High"));
        beacPage = beacPage.clickSortDropDown();
        Assert.assertTrue(beacPage.getSortDropDownValue().contains("High > Low"));
    }

    @Test
    public void testBeacPageDragAndDropPize() {
        System.out.println("---- testBeacPageDragAndDropPize ----");
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
            // TODO Auto-generated catch block
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
