package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends AbstractPage {

    private static final String URL = "https://google.com";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL);
    }

    public void search(String string) {
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys(string);
        searchField.submit();
    }

}
