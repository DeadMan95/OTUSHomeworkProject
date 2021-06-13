package applications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import weak_dependency.GamingConfiguration;
import weak_dependency.MultimediaConfiguration;
import weak_dependency.OfficeConfiguration;
import weak_dependency.PC;

@SpringBootApplication
public class TestWeakDependency {

    public static void main(String[] args) {
        SpringApplication.run(TestWeakDependency.class, args);
        PC pc4gamer = new PC(new GamingConfiguration());
        System.out.println(pc4gamer.getPC());
        PC pc4worker = new PC(new OfficeConfiguration());
        System.out.println(pc4worker.getPC());
        PC pc4family = new PC(new MultimediaConfiguration());
        System.out.println(pc4family.getPC());
    }

}
