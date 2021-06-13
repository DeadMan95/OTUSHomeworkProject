package applications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import strong_dependency.Car;

@SpringBootApplication
public class TestStrongDependency {

    public static void main(String[] args) {
        SpringApplication.run(TestStrongDependency.class, args);
        Car car4Family = new Car();
        System.out.println(car4Family.getCar());
        Car car4Young = new Car();
        System.out.println(car4Young.getCar());
        Car car4Boss = new Car();
        System.out.println(car4Boss.getCar());
    }

}
