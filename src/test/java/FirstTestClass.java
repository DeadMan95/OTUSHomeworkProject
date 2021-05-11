import interfaces.MyConfig;
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
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FirstTestClass {

    private final Logger logger = LogManager.getLogger(FirstTestClass.class);
    private final MyConfig config = ConfigFactory.create(MyConfig.class);
    private WebDriverWait wait;
    private WebDriver driver;

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

    @Test(description = "Yandex Market test")
    public void testYandex() {
        driver.get("https://market.yandex.ru/");
        moveToCategory("Электроника");
        moveToCatalog("Смартфоны");
        selectVendor("Samsung");
        selectVendor("Xiaomi");
        driver.findElement(By.xpath("//button[text()='по цене']")).click();
        checkMessage(addToCompare("Samsung"));
        checkMessage(addToCompare("Xiaomi"));
        driver.get("https://market.yandex.ru/my/compare-lists");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Сравнение товаров']")));
        Assert.assertEquals(driver.findElements(By.xpath("//div[@data-tid='412661c']")).size(), 2, "Проверка кол-ва товаров в сравнении");
    }

    private String addToCompare(String product) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@data-tid='67d9be0a']")));
        List<WebElement> elementList = driver.findElements(By.xpath("//article[@data-autotest-id='product-snippet']"));
        WebElement currentElement;
        int i = 0;
        while (true) {
            currentElement = elementList.get(i).findElement(By.xpath("./div[4]/div[1]/h3/a/span"));
            if (currentElement.getText().contains(product)) {
                focusElement(currentElement);
                elementList.get(i).findElement(By.xpath("./div[2]/div[2]")).click();
                break;
            }
            i++;
        }
        return currentElement.getText();
    }

    private void focusElement(WebElement element){
        Actions action = new Actions(driver);
        Actions build = action.moveToElement(element);
        build.perform();
    }

    private Map<String, WebElement> getCategoryMap() {
        Map<String, WebElement> categoryMap = new HashMap<>();
        for (WebElement elem : driver.findElements(By.xpath("//div[@data-zone-name='category-link']/div/a/span"))) {
            categoryMap.put(elem.getText(), elem);
        }
        return categoryMap;
    }

    private void moveToCategory(String categoryName) {
        getCategoryMap().get(categoryName).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Электроника']")));
    }

    //не учитываются общие каталоги и скрытые под "Еще..."
    private Map<String, WebElement> getCatalogMap() {
        Map<String, WebElement> catalogMap = new HashMap<>();
        for (WebElement elem : driver.findElements(By.xpath("//li/div/a"))) {
            catalogMap.put(elem.getText(), elem);
        }
        return catalogMap;
    }

    private void moveToCatalog(String catalogName) {
        getCatalogMap().get(catalogName).click();
    }

    //не учитываются скрытые под "Показать всё"
    private Map<String, WebElement> getVendorMap() {
        Map<String, WebElement> vendorInputMap = new HashMap<>();
        for (WebElement elem : driver.findElements(By.xpath("//fieldset[@data-autotest-id='7893318']/ul/li"))) {
            vendorInputMap.put(elem.findElement(By.xpath("./div/a/label/div/span")).getText(), elem.findElement(By.xpath("./div/a/label/div")));
        }
        return vendorInputMap;
    }

    private void selectVendor(String vendorName) {
        getVendorMap().get(vendorName).click();
    }

    private void checkMessage(String productName) {
        By messageLocator = By.xpath("//*[@data-tid='11882e1c']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageLocator));
        WebElement message = driver.findElement(messageLocator);
        String messageText = message.findElement(By.xpath("div/div[2]/div[1]")).getText();
        Assert.assertTrue(messageText.contains(productName.split(",")[0]), "Проверка сообщения о добавлении товара в список вравнения");
        message.findElement(By.xpath(".//button")).click();
    }


//    Открыть в Chrome сайт Яндекс.Маркет - "Электроника"-> "Смартфоны"
//    Отфильтровать список товаров: Samsung и Xiaomi
//    Отсортировать список товаров по цене (от меньшей к большей)
//    Добавить первый в списке Samsung
//    Проверить, что отобразилась плашка "Товар {имя товара} добавлен к сравнению"
//    Добавить первый в списке Xiaomi
//    Проверить, что отобразилась плашка "Товар {имя товара} добавлен к сравнению"
//    Перейти в раздел Сравнение
//    Проверить, что в списке товаров 2 позиции

}