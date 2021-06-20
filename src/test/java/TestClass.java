import interfaces.AboutMePageConfig;
import interfaces.TestDataConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AboutMePage;
import pages.MainPage;
import utilities.Setup;

public class TestClass extends Setup {

    private final TestDataConfig td = ConfigFactory.create(TestDataConfig.class);
    private final AboutMePageConfig cfg = ConfigFactory.create(AboutMePageConfig.class);


    private final String FIRST_NAME = td.firstName();
    private final String SUR_NAME = td.lastName();
    private final String NICK_NAME = td.nickName();
    private final String BIRTH_DATE = td.birthDate();
    private final String COUNTRY = td.country();
    private final String CITY = td.city();
    private final String ENG_LEVEL = td.engLevel();
    private final String SKYPE_CONTACT_TYPE = td.skypeContactType();
    private final String VK_CONTACT_TYPE = td.vkContactType();
    private final String SKYPE_CONTACT_VALUE = td.skypeContactValue();
    private final String VK_CONTACT_VALUE = td.vkContactValue();
    private final String LOGIN = td.login();
    private final String PASSWORD = td.password();

    @Test
    public void testOTUS() {
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.open().goToLoginPage().logIn(LOGIN, PASSWORD);
        AboutMePage aboutMePage = new AboutMePage(driver, wait);
        aboutMePage.open().setPersonalData(FIRST_NAME, SUR_NAME, NICK_NAME, BIRTH_DATE);
        aboutMePage.setLocationData(COUNTRY, CITY, ENG_LEVEL);
        aboutMePage.deleteContacts();
        aboutMePage.setContact(SKYPE_CONTACT_TYPE, SKYPE_CONTACT_VALUE);
        aboutMePage.setContact(VK_CONTACT_TYPE, VK_CONTACT_VALUE);
        aboutMePage.saveAndQuit();
        clean();
        closeUp();
        startUp();
        mainPage = new MainPage(driver, wait);
        mainPage.open().goToLoginPage().logIn(LOGIN, PASSWORD);
        aboutMePage = new AboutMePage(driver, wait);
        aboutMePage.open();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(FIRST_NAME, driver.findElement(By.name(cfg.firstNameFieldXpath())).getAttribute("value"), "First name assertion error!");
        softAssert.assertEquals(SUR_NAME, driver.findElement(By.name(cfg.lastNameFieldXpath())).getAttribute("value"), "Last name assertion error!");
        softAssert.assertEquals(NICK_NAME, driver.findElement(By.name(cfg.blogNameFieldXpath())).getAttribute("value"), "Blog name assertion error!");
        softAssert.assertEquals(BIRTH_DATE, driver.findElement(By.name(cfg.birthDateFieldXpath())).getAttribute("value"), "Birth date assertion error!");
        softAssert.assertEquals(COUNTRY, driver.findElement(By.xpath(cfg.countryComboboxXpath())).getText(), "Country assertion error!");
        softAssert.assertEquals(CITY, driver.findElement(By.xpath(cfg.cityComboboxXpath())).getText(), "City assertion error!");
        softAssert.assertEquals(ENG_LEVEL, driver.findElement(By.xpath(cfg.engLevelComboboxXpath())).getText(), "English level assertion error!");
        softAssert.assertEquals(SKYPE_CONTACT_TYPE, driver.findElement(By.xpath(cfg.thirdContactTypeComboBoxXpath())).getText(), "Skype type assertion error!");
        softAssert.assertEquals(SKYPE_CONTACT_VALUE, driver.findElement(By.id(cfg.thirdContactValueId())).getAttribute("value"), "Skype value assertion error!");
        softAssert.assertEquals(VK_CONTACT_TYPE, driver.findElement(By.xpath(cfg.forthContactTypeComboBoxXpath())).getText(), "VK type assertion error!");
        softAssert.assertEquals(VK_CONTACT_VALUE, driver.findElement(By.id(cfg.forthContactValueId())).getAttribute("value"), "Vk value assertion error!");

    }

}