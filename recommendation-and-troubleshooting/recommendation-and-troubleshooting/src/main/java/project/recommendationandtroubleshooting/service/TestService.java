package project.recommendationandtroubleshooting.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.model.TestModel;
import project.recommendationandtroubleshooting.model.troubleshooting.Bug;
import project.recommendationandtroubleshooting.model.troubleshooting.Description;
import project.recommendationandtroubleshooting.model.troubleshooting.Problem;
import project.recommendationandtroubleshooting.model.troubleshooting.Solution;

import java.util.HashSet;
import java.util.Set;

@Service
public class TestService {

    private final KieContainer kieContainer;

    @Autowired
    public TestService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public TestModel getResponse(TestModel model) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(model);
        kieSession.fireAllRules();
        kieSession.dispose();
        return model;
    }

    public Problem testTroubleshooting() {

        Set<String> desc1 = new HashSet<>();
        desc1.add("Računar ne može da se upali.");
        desc1.add("Računar ne reaguje na power dugme.");
        Set<String> sol1 = new HashSet<>();
        sol1.add("2.Proveriti da li je računar uključen u struju i proveriti ispravnost power dugmeta.");
        sol1.add("1.Odneti na dalju dijagnostiku u servis.");
        Bug bug1 = new Bug(desc1, sol1);

        Set<String> desc2 = new HashSet<>();
        desc2.add("Računar ne može da se upali.");
        desc2.add("Računar ne prolazi POST.");
        Set<String> sol2 = new HashSet<>();
        sol2.add("3.Neka periferija računara je blokirana, proveriti priključene uređaje.");
        sol2.add("2.Izvaditi RAM iz memorije da bi se NV-RAM ponovo napunio i zatim ponovo resetovati računar.");
        sol2.add("1.Neka komponenta računara crkava i daje loš napon, odneti na dijagnostiku u servis.");
        Bug bug2 = new Bug(desc2, sol2);

        Set<String> desc3 = new HashSet<>();
        desc3.add("Računar ne može da se upali.");
        desc3.add("Računar ne reaguje na power dugme.");
        Problem problem = new Problem(desc3);

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(bug1);
        kieSession.insert(bug2);
        kieSession.insert(problem);
        kieSession.getAgenda().getAgendaGroup("troubleshooting").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
        return problem;
    }
}
