import pages.MainPage;

import org.testng.annotations.Test;
import utilities.Setup;


public class TestClass extends Setup {

    private final String login = "doreshin@rtech.ru";
    private final String password = "ZA2dFYBURMgH";

    @Test
    public void testOtus() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open().goToLoginPage().logIn(login, password);
    }

}