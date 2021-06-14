package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AboutMePage extends AbstractPage {

    private final String aboutMePageURL = "https://otus.ru/lk/biography/personal/";

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
        clearAndSetData(driver.findElement(By.name("fname")), firstName);
        clearAndSetData(driver.findElement(By.name("lname")), surName);
        clearAndSetData(driver.findElement(By.name("blog_name")), nickName);
        clearAndSetData(driver.findElement(By.name("date_of_birth")), birthDate);
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
                driver.findElement(By.xpath("//input[@name='country']/following-sibling::*")),
                driver.findElement(By.xpath("//button[@title='" + nameOfCountry + "']/parent::*")),
                nameOfCountry);
        chooseLocationData(
                driver.findElement(By.xpath("//input[@name='city']/following-sibling::*")),
                driver.findElement(By.xpath("//button[@title='" + nameOfCity + "']/parent::*")),
                nameOfCity);
        chooseLocationData(
                driver.findElement(By.xpath("//input[@name='english_level']/following-sibling::*")),
                driver.findElement(By.xpath("//button[@title='" + nameOfEngLevel + "']/parent::*")),
                nameOfEngLevel);
    }

    public void deleteContacts() {
        WebElement block = driver.findElement(By.xpath("//div[@class='lk-cv-block__line js-formset-items']"));
        List<WebElement> listOfContacts = block.findElements(By.xpath("./div"));
        if (listOfContacts.size() > 0) {
            for (WebElement elem : listOfContacts
            ) {
                elem.findElement(By.xpath("./div[3]")).findElement(By.xpath(".//button[text()='Удалить']")).click();
            }
        }
    }

    public void setContact(String contactType, String contactValue) {
        driver.findElement(By.xpath("//button[text()='Добавить']")).click();
        WebElement block = driver.findElement(By.xpath("//div[@class='lk-cv-block__line js-formset-items']"));
        List<WebElement> listOfContacts = block.findElements(By.xpath("./div"));
        WebElement lastContactBlock = listOfContacts.get(listOfContacts.size() - 1);
        lastContactBlock.findElement(By.xpath(".//div[@class[contains(.,'js-lk-cv-custom-select')]]")).click();
        List<WebElement> listOfContactType = lastContactBlock
                .findElement(By.xpath(".//div[@class[contains(.,'lk-cv-block__select-scroll')]]"))
                .findElements(By.tagName("button"));
        for (WebElement elem : listOfContactType) {
            String cityName = elem.getText();
            if (cityName.equals(contactType)) elem.click();
        }
        lastContactBlock.findElement(By.xpath(".//input[@class[contains(.,'input_straight-top-left')]]"))
                .sendKeys(contactValue);
    }

    public void saveAndQuit() {
        driver.findElement(By.xpath("//button[@title='Сохранить и заполнить позже']")).click();
    }



}
