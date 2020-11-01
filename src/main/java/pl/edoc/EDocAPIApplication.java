package pl.edoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EDocAPIApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(EDocAPIApplication.class);

        String profile = System.getenv("PROFILE");
        if (profile == null) {
            app.setAdditionalProfiles("dev");
        } else {
            app.setAdditionalProfiles(profile);
        }
        app.run(args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
