package interfaces;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:src/main/resources/LoginPageConfig.properties"})
public interface LoginPageConfig extends Config {

    String authFormXpath();

    String loginFieldXpath();

    String passwordFieldXpath();

    String myCoursesXpath();
}
