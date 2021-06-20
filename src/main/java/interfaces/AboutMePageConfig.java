package interfaces;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:src/main/resources/AboutMePageConfig.properties"})
public interface AboutMePageConfig extends Config {

    String saveButtonXpath();

    String contactFieldXpath();

    String firstNameFieldXpath();

    String lastNameFieldXpath();

    String blogNameFieldXpath();

    String birthDateFieldXpath();

    String contactBlockXpath();

    String addContactButtonXpath();

    String contactTypeComboboxXpath();

    String listOfValuesXpath();

    String deleteButtonXpath();

    String countryComboboxXpath();

    String cityComboboxXpath();

    String engLevelComboboxXpath();

    String aboutMePageURL();

    String thirdContactTypeComboBoxXpath();

    String thirdContactValueId();

    String forthContactTypeComboBoxXpath();

    String forthContactValueId();

}
