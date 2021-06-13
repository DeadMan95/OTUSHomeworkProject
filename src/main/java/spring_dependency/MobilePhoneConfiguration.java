package spring_dependency;

import interfaces.Configurable;

public class MobilePhoneConfiguration implements Configurable {
    @Override
    public String getConfiguration() {
        return "MobilePhone";
    }
}
