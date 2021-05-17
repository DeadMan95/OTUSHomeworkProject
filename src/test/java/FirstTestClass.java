import interfaces.TestData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstTestClass {

    private WebDriverWait wait;
    private WebDriver driver;

    private final Logger logger = LogManager.getLogger(FirstTestClass.class);
    private final TestData config = ConfigFactory.create(TestData.class);

    private final String sortByPriceButton = "//button[text()='по цене']";
    private final String categoryLinkXPath = "//div[@data-zone-name='category-link']/div/a/span";
    private final String catalogLinkXPath = "//li/div/a";
    private final String vendorCheckBoxXPath = "//fieldset[@data-autotest-id='7893318']/ul/li";
    private final String vendorNameXPath = "./div/a/label/div/span";
    private final String vendorDivXPath = "./div/a/label/div";
    private final String messageLocatorXPath = "//*[@data-tid='11882e1c']";
    private final String messageTextXPath = "div/div[2]/div[1]";
    private final String compareButtonXPath = ".//button";
    private final String messageFormXPath = "//*[@data-tid='67d9be0a']";
    private final String productElementXPath = "//article[@data-autotest-id='product-snippet']";
    private final String productNameXPath = "./div[4]/div[1]/h3/a/span";
    private final String addToCompareButtonXPath = "./div[2]/div[2]";
    private final String comparePageTitleXPath = "//div[text()='Сравнение товаров']";
    private final String productElementIntoCompareXPath = "//div[@data-tid='412661c']";


    @BeforeMethod
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        logger.info("Driver initialized!");
    }

    @AfterMethod
    public void closeUp() {
        if (driver != null) driver.quit();
        logger.info("Driver closed!");
    }


    @Test(description = "Yandex Market Test")
    public void testYandex() {
        //Открыть в Chrome сайт Яндекс.Маркет - "Электроника"-> "Смартфоны"
        driver.get(config.yandexMarketURL());
        moveToCategory(config.electronicCategory());
        moveToCatalog(config.smartphoneCatalog());

        //Отфильтровать список товаров: Samsung и Xiaomi
        selectVendor(config.samsungProduct());
        selectVendor(config.xiaomiProduct());

        //Отсортировать список товаров по цене (от меньшей к большей)
        driver.findElement(By.xpath(sortByPriceButton)).click();

        //Добавить первый в списке Samsung
        //Проверить, что отобразилась плашка "Товар {имя товара} добавлен к сравнению"
        checkMessage(addToCompare(config.samsungProduct()));

        //Добавить первый в списке Xiaomi
        // Проверить, что отобразилась плашка "Товар {имя товара} добавлен к сравнению"
        checkMessage(addToCompare(config.xiaomiProduct()));

        //Перейти в раздел Сравнение
        driver.get(config.compareListURL());

        //Проверить, что в списке товаров 2 позиции
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(comparePageTitleXPath)));
        Assert.assertEquals(driver.findElements(By.xpath(productElementIntoCompareXPath)).size(), 2, "Проверка кол-ва товаров в сравнении");
    }


    private void moveToCategory(String categoryName) {
        getCategoryMap().get(categoryName).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='" + categoryName + "']")));
    }

    private Map<String, WebElement> getCategoryMap() {
        Map<String, WebElement> categoryMap = new HashMap<>();
        for (WebElement elem : driver.findElements(By.xpath(categoryLinkXPath))) {
            categoryMap.put(elem.getText(), elem);
        }
        return categoryMap;
    }

    private void moveToCatalog(String catalogName) {
        getCatalogMap().get(catalogName).click();
    }

    //не учитываются общие каталоги и скрытые под "Еще..."
    private Map<String, WebElement> getCatalogMap() {
        Map<String, WebElement> catalogMap = new HashMap<>();
        for (WebElement elem : driver.findElements(By.xpath(catalogLinkXPath))) {
            catalogMap.put(elem.getText(), elem);
        }
        return catalogMap;
    }

    private void selectVendor(String vendorName) {
        getVendorMap().get(vendorName).click();
    }

    //не учитываются скрытые под "Показать всё"
    private Map<String, WebElement> getVendorMap() {
        Map<String, WebElement> vendorInputMap = new HashMap<>();
        for (WebElement elem : driver.findElements(By.xpath(vendorCheckBoxXPath))) {
            vendorInputMap.put(elem.findElement(By.xpath(vendorNameXPath)).getText(), elem.findElement(By.xpath(vendorDivXPath)));
        }
        return vendorInputMap;
    }

    private void checkMessage(String productName) {
        By messageLocator = By.xpath(messageLocatorXPath);
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageLocator));
        WebElement message = driver.findElement(messageLocator);
        String messageText = message.findElement(By.xpath(messageTextXPath)).getText();
        Assert.assertTrue(messageText.contains(productName.split(",")[0]), "Проверка сообщения о добавлении товара в список вравнения");
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageLocator));
        wait.until(ExpectedConditions.elementToBeClickable(messageLocator));
        message.findElement(By.xpath(compareButtonXPath)).click();
    }

    private String addToCompare(String product) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(messageFormXPath)));
        List<WebElement> elementList = driver.findElements(By.xpath(productElementXPath));
        WebElement currentElement;
        int i = 0;
        while (true) {
            currentElement = elementList.get(i).findElement(By.xpath(productNameXPath));
            if (currentElement.getText().contains(product)) {
                focusElement(currentElement);
                elementList.get(i).findElement(By.xpath(addToCompareButtonXPath)).click();
                break;
            }
            i++;
        }
        return currentElement.getText();
    }

    private void focusElement(WebElement element) {
        new Actions(driver).moveToElement(element).perform();
    }

}