package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends AbstractPage {

    private By authFormLocator = By.xpath("//form[@action='/login/']");
    private By loginFieldLocator = By.xpath(".//input[@name='email']");
    private By passwordFieldLocator = By.xpath(".//input[@name='password']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MainPage logIn(String login, String password) {

        driver.findElement(authFormLocator).findElement(loginFieldLocator).sendKeys(login);
        driver.findElement(authFormLocator).findElement(passwordFieldLocator).sendKeys(password);
        driver.findElement(authFormLocator).findElement(loginFieldLocator).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new MainPage(driver);
    }

}
