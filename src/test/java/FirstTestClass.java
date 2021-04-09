import interfaces.MyConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

    @Test(description = "title check")
    public void testOTUS() {
        driver.get("http://otus.ru");
        logger.info("URL opened!");
        Assert.assertEquals(driver.getTitle(), config.title());
        logger.info("It's really OTUS website!");
    }

}