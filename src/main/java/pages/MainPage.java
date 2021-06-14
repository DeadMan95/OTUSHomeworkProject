package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends AbstractPage {

    private final String mainPageURL = "https://otus.ru/";
    private final By signUpButtonLocator = By.xpath("//button/span[text()=' и регистрация']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open(){
        driver.get(mainPageURL);
        return new MainPage(driver);
    }

    public LoginPage goToLoginPage(){
        driver.findElement(signUpButtonLocator).click();
        return new LoginPage(driver);
    }

}
