package project.recommendationandtroubleshooting.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import project.recommendationandtroubleshooting.model.Person;
import project.recommendationandtroubleshooting.model.troubleshooting.Bug;
import project.recommendationandtroubleshooting.model.troubleshooting.Description;
import project.recommendationandtroubleshooting.model.troubleshooting.Solution;
import project.recommendationandtroubleshooting.repository.BugRepository;
import project.recommendationandtroubleshooting.repository.DescriptionRepository;
import project.recommendationandtroubleshooting.repository.PersonRepository;
import project.recommendationandtroubleshooting.repository.SolutionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class DroolsConfig {

    private static final Logger logger = LogManager.getLogger(DroolsConfig.class);

    @Autowired
    private KieSessionManager kieSessionManager;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DescriptionRepository descriptionRepository;

    @Autowired
    private SolutionRepository solutionRepository;

    @Autowired
    private BugRepository bugRepository;

    @Bean
    public KieContainer kieContainer() {
        logger.info("Creating new Kie Container.");
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
        KieContainer kieContainer = this.kieContainer();
        KieSession kieSession = kieContainer.newKieSession("rulesSession");
        logger.info("Creating new Kie Session.");

        List<Person> allUsers = personRepository.findAll();
        for (Person user : allUsers) {
            kieSession.insert(user);
        }

        List<Description> allDescriptions = descriptionRepository.findAll();
        for (Description description : allDescriptions) {
            kieSession.insert(description);
        }

        List<Solution> allSolutions = solutionRepository.findAll();
        for (Solution solution : allSolutions) {
            kieSession.insert(solution);
        }

        List<Bug> allBugs = bugRepository.findAll();
        for (Bug bug : allBugs) {
            kieSession.insert(bug);
        }

        this.kieSessionManager.insert(kieSession);
        return kieSession;
    }

}
