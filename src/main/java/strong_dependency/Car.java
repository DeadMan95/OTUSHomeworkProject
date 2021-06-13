package strong_dependency;

public class Car {

    private ClassicConfiguration classicConfiguration;
    private SportConfiguration sportConfiguration;
    private LuxeConfiguration luxeConfiguration;

    public String getCar() {
        //classicConfiguration = new ClassicConfiguration();
        //return "Car with " + classicConfiguration.getConfiguration() + " configuration is ready!";

        //sportConfiguration = new SportConfiguration();
        //return "Car with " + sportConfiguration.getConfiguration() + " configuration is ready!";

        luxeConfiguration = new LuxeConfiguration();
        return "Car with " + luxeConfiguration.getConfiguration() + " configuration is ready!";
    }
}
