package applications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_dependency.Phone;
import spring_dependency.SpringConfig;

@SpringBootApplication
public class TestSpringDependency {

    public static void main(String[] args) {
        SpringApplication.run(TestSpringDependency.class, args);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        Phone phone4student = context.getBean("smartPhone", Phone.class);
        Phone phone4businessman = context.getBean("mobilePhone", Phone.class);
        Phone phone4granny = context.getBean("telephone", Phone.class);

        System.out.println(phone4student.getPhone());
        System.out.println(phone4businessman.getPhone());
        System.out.println(phone4granny.getPhone());
    }

}
