package interfaces;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:src/main/resources/TestDataConfig.properties"})
public interface TestDataConfig extends Config {

    String firstName();

    String lastName();

    String nickName();

    String birthDate();

    String country();

    String city();

    String engLevel();

    String skypeContactType();

    String vkContactType();

    String skypeContactValue();

    String vkContactValue();

    String login();

    String password();

}
