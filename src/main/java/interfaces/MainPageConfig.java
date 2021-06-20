package interfaces;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:src/main/resources/MainPageConfig.properties"})
public interface MainPageConfig extends Config {

    String mainPageURL();

    String signUpButtonXpath();

}
