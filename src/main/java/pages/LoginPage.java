package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPage {

    private By authFormLocator = By.xpath("//form[@action='/login/']");
    private By loginFieldLocator = By.xpath(".//input[@name='email']");
    private By passwordFieldLocator = By.xpath(".//input[@name='password']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public MainPage logIn(String login, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(authFormLocator));
        driver.findElement(authFormLocator).findElement(loginFieldLocator).sendKeys(login);
        driver.findElement(authFormLocator).findElement(passwordFieldLocator).sendKeys(password);
        driver.findElement(authFormLocator).findElement(passwordFieldLocator).submit();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Мои курсы']")));
        return new MainPage(driver, wait);
    }

}
