package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HomePage;
import pages.SearchResultsPage;
import utilities.WebDriverFactory;
import utilities.WebDriverType;

import java.util.concurrent.TimeUnit;

public class CucumberSteps {

    HomePage homePage;
    SearchResultsPage searchResultsPage;

    private final Logger logger = LogManager.getLogger(CucumberSteps.class);
    protected WebDriver driver;

    @Before
    public void startUp() {
        driver = WebDriverFactory.createDriver(WebDriverType.CHROME);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        logger.info("Driver initialized!");
    }

    @After
    public void closeUp() {
        if (driver != null) driver.quit();
        logger.info("Driver closed!");
    }

    @Given("I go to home page")
    public void iGoToHomePage() {
        homePage = new HomePage(driver);
        homePage.open();
    }

    @When("I type {string} and submit")
    public void iTypeAndSubmit(String searchString) {
        homePage.search(searchString);
    }

    @Then("I see page with {string} title")
    public void iSeePageWithTitle(String pageTitle) {
        searchResultsPage = new SearchResultsPage(driver);
        Assert.assertTrue(searchResultsPage.checkTitle(pageTitle));
    }

}
