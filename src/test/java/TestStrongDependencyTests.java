import org.junit.jupiter.api.Test;
import org.testng.Assert;
import strong_dependency.Car;

class TestStrongDependencyTests {

    @Test
    void test() {
        Car car4Boss = new Car();
        Assert.assertEquals(car4Boss.getCar(), "Car with Luxe configuration is ready!", "Assertion error!");
    }

}
