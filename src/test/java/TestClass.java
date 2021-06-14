import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.AboutMePage;
import pages.MainPage;

import org.testng.annotations.Test;
import utilities.Setup;
import utilities.WebDriverFactory;
import utilities.WebDriverType;

public class TestClass extends Setup {

    public static final String FIRST_NAME = "Данила";
    private final String login = "doreshin@rtech.ru";
    private final String password = "ZA2dFYBURMgH";

    @Test
    public void testOTUS() {
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.open().goToLoginPage().logIn(login, password);
        AboutMePage aboutMePage = new AboutMePage(driver, wait);
        aboutMePage.open().setPersonalData(FIRST_NAME, "Орешин", "Данила", "10.02.1995");
        //aboutMePage.setLocationData("Россия", "Москва", "Начальный уровень (Beginner)");
        aboutMePage.deleteContacts();
        aboutMePage.setContact("Skype", "tester");
        aboutMePage.setContact("VK", "vk.com/test");
        aboutMePage.saveAndQuit();
        clean();
        closeUp();
        startUp();
        //driver = WebDriverFactory.createDriver(WebDriverType.CHROME);
        mainPage = new MainPage(driver, wait);
        mainPage.open().goToLoginPage().logIn(login, password);
        aboutMePage = new AboutMePage(driver, wait);
        aboutMePage.open();
        //WebElement test = driver.findElement(By.name("fname"));

        Assert.assertEquals(FIRST_NAME, driver.findElement(By.name("fname")).getAttribute("value"), "First name assertion error!");

    }

}