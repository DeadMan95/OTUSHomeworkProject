package weak_dependency;

import interfaces.Configurable;

public class GamingConfiguration implements Configurable {
    @Override
    public String getConfiguration() {
        return "Gaming";
    }
}
