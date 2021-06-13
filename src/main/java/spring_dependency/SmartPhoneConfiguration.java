package spring_dependency;

import interfaces.Configurable;

public class SmartPhoneConfiguration implements Configurable {
    @Override
    public String getConfiguration() {
        return "SmartPhone";
    }
}
