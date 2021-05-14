package interfaces;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:src/main/resources/TestData.properties"})
public interface TestData extends Config {

    @Key("yandex.url.market")
    String yandexMarketURL();

    @Key("yandex.url.compareList")
    String compareListURL();

    @Key("yandex.categoryName.electronic")
    String electronicCategory();

    @Key("yandex.catalogName.smartphone")
    String smartphoneCatalog();

    @Key("yandex.productName.samsung")
    String samsungProduct();

    @Key("yandex.productName.xiaomi")
    String xiaomiProduct();

}
