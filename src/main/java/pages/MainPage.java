package pages;

import interfaces.MainPageConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage {

    private final MainPageConfig cfg = ConfigFactory.create(MainPageConfig.class);

    private final String mainPageURL = cfg.mainPageURL();
    private final By signUpButtonLocator = By.xpath(cfg.signUpButtonXpath());

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public MainPage open() {
        driver.get(mainPageURL);
        return new MainPage(driver, wait);
    }

    public LoginPage goToLoginPage() {
        driver.findElement(signUpButtonLocator).click();
        return new LoginPage(driver, wait);
    }

}
