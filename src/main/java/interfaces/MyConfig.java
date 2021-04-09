package interfaces;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:src/main/resources/MyConfig.properties"})
public interface MyConfig extends Config {
    @Key("otus.website.title")
    String title();
}
