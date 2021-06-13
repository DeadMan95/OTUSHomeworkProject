package spring_dependency;

import interfaces.Configurable;

public class Phone {

    private Configurable configuration;

    public Phone(Configurable configuration) {
        this.configuration = configuration;
    }

    public String getPhone() {
        return "Phone with " + configuration.getConfiguration() + " configuration is ready!";
    }

}
