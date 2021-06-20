package pages;

import interfaces.AboutMePageConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Setup;

import java.util.List;

public class AboutMePage extends AbstractPage {

    private final Logger logger = LogManager.getLogger(Setup.class);
    private final AboutMePageConfig cfg = ConfigFactory.create(AboutMePageConfig.class);

    private final By saveButtonLocator = By.xpath(cfg.saveButtonXpath());
    private final By contactFieldLocator = By.xpath(cfg.contactFieldXpath());
    private final By firstNameFieldLocator = By.name(cfg.firstNameFieldXpath());
    private final By lastNameFieldLocator = By.name(cfg.lastNameFieldXpath());
    private final By blogNameFieldLocator = By.name(cfg.blogNameFieldXpath());
    private final By birthDateFieldLocator = By.name(cfg.birthDateFieldXpath());
    private final By contactBlockLocator = By.xpath(cfg.contactBlockXpath());
    private final By addContactButtonLocator = By.xpath(cfg.addContactButtonXpath());
    private final By contactTypeComboboxLocator = By.xpath(cfg.contactTypeComboboxXpath());
    private final By listOfValuesLocator = By.xpath(cfg.listOfValuesXpath());
    private final By deleteButtonLocator = By.xpath(cfg.deleteButtonXpath());
    private final By countryComboboxLocator = By.xpath(cfg.countryComboboxXpath());
    private final By cityComboboxLocator = By.xpath(cfg.cityComboboxXpath());
    private final By engLevelComboboxLocator = By.xpath(cfg.engLevelComboboxXpath());
    private final String aboutMePageURL = cfg.aboutMePageURL();


    public AboutMePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public AboutMePage open() {
        driver.get(aboutMePageURL);
        return new AboutMePage(driver, wait);
    }

    public void clearAndSetData(WebElement elem, String data) {
        elem.clear();
        elem.sendKeys(data);
    }

    public void setPersonalData(String firstName, String surName, String nickName, String birthDate) {
        logger.info("Adding new personal data...");
        clearAndSetData(driver.findElement(firstNameFieldLocator), firstName);
        clearAndSetData(driver.findElement(lastNameFieldLocator), surName);
        clearAndSetData(driver.findElement(blogNameFieldLocator), nickName);
        clearAndSetData(driver.findElement(birthDateFieldLocator), birthDate);
        logger.info("New personal data was added!");
    }

    public void chooseLocationData(WebElement comboBoxElem, WebElement subMenuElem, String data) {
        wait.until(ExpectedConditions.elementToBeClickable(comboBoxElem));
        comboBoxElem.click();
        wait.until(ExpectedConditions.visibilityOf(subMenuElem));
        List<WebElement> list = subMenuElem.findElements(By.tagName("button"));
        for (WebElement elem : list) {
            String elemName = elem.getText();
            //elemName = elemName.replaceAll("\\s+", "");
            if (elemName.equals(data)) elem.click();
        }
    }

    public void setLocationData(String nameOfCountry, String nameOfCity, String nameOfEngLevel) {
        chooseLocationData(
                driver.findElement(countryComboboxLocator),
                driver.findElement(By.xpath("//button[@title='" + nameOfCountry + "']/parent::*")),
                nameOfCountry);
        chooseLocationData(
                driver.findElement(cityComboboxLocator),
                driver.findElement(By.xpath("//button[@title='" + nameOfCity + "']/parent::*")),
                nameOfCity);
        chooseLocationData(
                driver.findElement(engLevelComboboxLocator),
                driver.findElement(By.xpath("//button[@title='" + nameOfEngLevel + "']/parent::*")),
                nameOfEngLevel);
        logger.info("New location data was added!");
    }

    public void deleteContacts() {
        WebElement block = driver.findElement(contactBlockLocator);
        List<WebElement> listOfContacts = block.findElements(By.xpath("./div"));
        if (listOfContacts.size() > 0) {
            for (WebElement elem : listOfContacts
            ) {
                elem.findElement(By.xpath("./div[3]")).findElement(deleteButtonLocator).click();
            }
        }
    }

    public void setContact(String contactType, String contactValue) {
        driver.findElement(addContactButtonLocator).click();
        WebElement block = driver.findElement(contactBlockLocator);
        List<WebElement> listOfContacts = block.findElements(By.xpath("./div"));
        WebElement lastContactBlock = listOfContacts.get(listOfContacts.size() - 1);
        lastContactBlock.findElement(contactTypeComboboxLocator).click();
        List<WebElement> listOfContactType = lastContactBlock.findElement(listOfValuesLocator).findElements(By.tagName("button"));
        for (WebElement elem : listOfContactType) {
            String typeOfContact = elem.getText();
            if (typeOfContact.equals(contactType)) elem.click();
        }
        lastContactBlock.findElement(contactFieldLocator).sendKeys(contactValue);
        logger.info("New contact data was added!");
    }

    public void saveAndQuit() {
        driver.findElement(saveButtonLocator).click();
    }

}
