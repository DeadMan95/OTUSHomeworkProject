package weak_dependency;

import interfaces.Configurable;

public class OfficeConfiguration implements Configurable {
    @Override
    public String getConfiguration() {
        return "Office";
    }
}
