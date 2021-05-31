package project.recommendationandtroubleshooting.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import project.recommendationandtroubleshooting.model.Person;
import project.recommendationandtroubleshooting.repository.PersonRepository;

import java.util.List;

@Configuration
public class DroolsConfig {

    @Autowired
    private KieSessionManager kieSessionManager;

    @Autowired
    private PersonRepository personRepository;

    @Bean
    public KieContainer kieContainer() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("project", "recommendation-and-troubleshooting-drools", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        kScanner.start(10_000);
        return kContainer;
    }

    @Bean
    @SessionScope
    public KieSession kieSession() {
        KieSession kieSession = this.kieContainer().newKieSession("rulesSession");
        System.out.println("Creating new kie session");
        List<Person> allUsers = personRepository.findAll();
        for (Person user : allUsers) {
            kieSession.insert(user);
        }

        this.kieSessionManager.insert(kieSession);
        return kieSession;
    }


}
