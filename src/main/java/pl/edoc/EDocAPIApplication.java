package pl.edoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EDocAPIApplication {

    public static final int EMAIL_SENDER_SCHEDULER_POOL_SIZE = 1;

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

    @Bean
    public ThreadPoolTaskScheduler emailSenderScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(EMAIL_SENDER_SCHEDULER_POOL_SIZE);
        threadPoolTaskScheduler.setThreadNamePrefix("EmailSenderScheduler");
        return threadPoolTaskScheduler;
    }

}
