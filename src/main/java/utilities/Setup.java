package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class Setup {

    private final Logger logger = LogManager.getLogger(Setup.class);
    protected WebDriver driver;

    @BeforeClass
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        logger.info("Driver initialized!");
    }

    @AfterClass
    public void closeUp() {
        if (driver != null) driver.quit();
        logger.info("Driver closed!");
    }

    @AfterMethod
    public void clean() {
        driver.manage().deleteAllCookies();
    }

}
