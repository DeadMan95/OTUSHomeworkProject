package spring_dependency;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("spring_dependency")
public class SpringConfig {

    @Bean
    public SmartPhoneConfiguration smartPhoneConfiguration() {
        return new SmartPhoneConfiguration();
    }

    @Bean
    public MobilePhoneConfiguration mobilePhoneConfiguration() {
        return new MobilePhoneConfiguration();
    }

    @Bean
    public TelephoneConfiguration telephoneConfiguration() {
        return new TelephoneConfiguration();
    }

    @Bean
    public Phone smartPhone() {
        return new Phone(smartPhoneConfiguration());
    }

    @Bean
    public Phone mobilePhone() {
        return new Phone(mobilePhoneConfiguration());
    }

    @Bean
    public Phone telephone() {
        return new Phone(telephoneConfiguration());
    }
}
