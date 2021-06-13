package weak_dependency;

import interfaces.Configurable;

public class PC {

    private Configurable configuration;

    public PC(Configurable configuration) {
        this.configuration = configuration;
    }

    public String getPC() {
        return "PC with " + configuration.getConfiguration() + " configuration is ready!";
    }

}
