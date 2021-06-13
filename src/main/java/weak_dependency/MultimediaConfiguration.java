package weak_dependency;

import interfaces.Configurable;

public class MultimediaConfiguration implements Configurable {
    @Override
    public String getConfiguration() {
        return "Multimedia";
    }
}
