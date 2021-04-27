import interfaces.MyConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.TestRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstTestClass {

    private final Logger logger = LogManager.getLogger(FirstTestClass.class);
    private final MyConfig config = ConfigFactory.create(MyConfig.class);
    private WebDriver driver;

    @BeforeMethod
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Driver initialized!");
    }

    @AfterMethod
    public void closeUp() {
        if (driver != null) driver.quit();
        logger.info("Driver closed!");
    }

    @Test(description = "test #1")
    public void checkAddress() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Откройте сайт https://otus.ru;
        driver.get(config.otusURL());
        //перейти во вкладку "Контакты"
        driver.findElement(By.xpath(".//a[@class[contains(.,'header2_subheader-link')] and @title='Контакты']")).click();
        WebElement address = driver.findElement(By.xpath("//div[.='Адрес']/following-sibling::div"));
        //и проверить адрес: 125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02;
        Assert.assertEquals(address.getText(), config.otusFullAddress());
        //разверните окно браузера на полный экран(не киоск);
        driver.manage().window().maximize();
        //проверьте title страницы.
        Assert.assertEquals(driver.getTitle(), config.otusContactPageTitle());
        logger.info("Test successfully completed!");
    }

    @Test(description = "test #2")
    public void checkPhoneNumbers() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //Перейти на сайт теле2 страница https://msk.tele2.ru/shop/number
        driver.get(config.tele2URL());
        //ввести в поле "поиск номера" 97 и начать поиск;
        By searchFieldLocator = By.id("searchNumber");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchFieldLocator));
        driver.findElement(searchFieldLocator).sendKeys("97");

        /*не очень понятно, что имелось в виду в задании. возможно задача была составлена, когда сайт теле2 выглядел как-то по-другому.
        сейчас номера присутсвуют ДО поиска, поиск осужествляется по мере воожа символов. Т.е. до поиска и после состав элементов страницы идентичен.
        завязаться на появление новых, отличных от дефолта элементов, не выйдет, т.к. их нет, а завязка на уже существующие не даст рез-та,
        т.к. драйвер моментально найдет уже существующие элементы еще до осуществления поиска...
        в связи с этим кейс достаточно странный, если не сказать - бессмысленный. был бы смысл, если бы поиск производился по нажатию кнопки*/

        //дождаться появления номеров.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='bundles-column'][5]")));
        logger.info("Test successfully completed!");
    }

    @Test(description = "test #3")
    public void checkAnswer() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Перейдите на сайт https://otus.ru;
        driver.get(config.otusURL());
        //перейдите на F.A.Q
        driver.findElement(By.xpath(".//a[@class[contains(.,'header2_subheader-link')] and @title='FAQ']")).click();
        //нажмите на вопрос: "Где посмотреть программу интересующего курса?";
        List<WebElement> elementList = driver.findElements(By.xpath("//div[@data-linked-id=6]/div[@class[contains(.,'faq-question')]]"));
        String text = null;
        for (WebElement elem : elementList) {
            if (elem.getText().equals(config.otusQuestion())) {
                elem.click();
                text = elem.findElement(By.xpath("./div[2]")).getText();
            }
        }
        //проверьте, что текст соответствует следующему: "Программу курса в сжатом виде можно увидеть на странице курса после блока с преподавателями.
        //Подробную программу курса можно скачать кликнув на “Скачать подробную программу курса”.
        Assert.assertEquals(text, config.otusAnswer());
        logger.info("Test successfully completed!");
    }

    @Test(description = "test #4")
    public void subscribeTest() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Зайдите на сайт https://otus.ru;
        driver.get(config.otusURL());
        //заполните тестовый почтовый ящик в поле "Подпишитесь на наши новости";
        WebElement footer = driver.findElement(By.tagName("footer"));
        footer.findElement(By.name("email")).sendKeys(config.testEmail());
        //нажмите кнопку "Подписаться";
        footer.findElement(By.tagName("button")).click();
        //проверьте, что появилось сообщение: "Вы успешно подписались".
        String message = footer.findElement(By.xpath("//p[@class='subscribe-modal__success']")).getText();
        Assert.assertEquals(message, config.subscribeMessage());
        logger.info("Test successfully completed!");
    }

}