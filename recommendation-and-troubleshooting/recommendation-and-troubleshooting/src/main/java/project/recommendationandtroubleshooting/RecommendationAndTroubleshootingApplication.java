package project.recommendationandtroubleshooting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RecommendationAndTroubleshootingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecommendationAndTroubleshootingApplication.class, args);
    }
}
