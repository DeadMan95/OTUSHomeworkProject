package pages;

import interfaces.LoginPageConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Setup;

public class LoginPage extends AbstractPage {

    private final Logger logger = LogManager.getLogger(Setup.class);

    private final LoginPageConfig cfg = ConfigFactory.create(LoginPageConfig.class);

    private final By authFormLocator = By.xpath(cfg.authFormXpath());
    private final By loginFieldLocator = By.xpath(cfg.loginFieldXpath());
    private final By passwordFieldLocator = By.xpath(cfg.passwordFieldXpath());
    private final By myCoursesLocator = By.xpath(cfg.myCoursesXpath());

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public MainPage logIn(String login, String password) {
        logger.info("Trying to logging in...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(authFormLocator));
        driver.findElement(authFormLocator).findElement(loginFieldLocator).sendKeys(login);
        driver.findElement(authFormLocator).findElement(passwordFieldLocator).sendKeys(password);
        driver.findElement(authFormLocator).findElement(passwordFieldLocator).submit();
        wait.until(ExpectedConditions.elementToBeClickable(myCoursesLocator));
        logger.info("Successfully logged in!");
        return new MainPage(driver, wait);
    }

}
