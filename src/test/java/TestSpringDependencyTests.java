import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import spring_dependency.Phone;
import spring_dependency.SpringConfig;

@SpringBootTest(classes = SpringConfig.class)
class TestSpringDependencyTests {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    @Test
    void test() {

        Phone phone4student = context.getBean("smartPhone", Phone.class);
        Assert.assertEquals(phone4student.getPhone(), "Phone with SmartPhone configuration is ready!", "Assertion error!");
    }

}
