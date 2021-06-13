package spring_dependency;

import interfaces.Configurable;

public class TelephoneConfiguration implements Configurable {
    @Override
    public String getConfiguration() {
        return "Telephone";
    }
}
