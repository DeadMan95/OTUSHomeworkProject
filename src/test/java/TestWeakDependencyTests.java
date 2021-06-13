import org.junit.jupiter.api.Test;
import org.testng.Assert;
import weak_dependency.GamingConfiguration;
import weak_dependency.PC;

class TestWeakDependencyTests {

    @Test
    void test() {
        PC pc = new PC(new GamingConfiguration());
        Assert.assertEquals(pc.getPC(), "PC with Gaming configuration is ready!", "Assertion error!");
    }

}
