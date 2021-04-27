package interfaces;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:src/main/resources/MyConfig.properties"})
public interface MyConfig extends Config {
    @Key("otus.main.title")
    String otusMainPageTitle();

    @Key("otus.contact.title")
    String otusContactPageTitle();

    @Key("otus.main.url")
    String otusURL();

    @Key("tele2.number.url")
    String tele2URL();

    @Key("otus.contact.address")
    String otusFullAddress();

    @Key("otus.faq.question")
    String otusQuestion();

    @Key("otus.faq.answer")
    String otusAnswer();

    @Key("otus.subscribe.message")
    String subscribeMessage();

    @Key("test.email")
    String testEmail();
}
