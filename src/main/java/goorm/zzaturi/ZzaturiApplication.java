package goorm.zzaturi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ZzaturiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzaturiApplication.class, args);
    }

}
