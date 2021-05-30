package project.recommendationandtroubleshooting.service;

import org.drools.core.time.SessionPseudoClock;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.enums.LimitType;
import project.recommendationandtroubleshooting.model.TestModel;
import project.recommendationandtroubleshooting.model.User;
import project.recommendationandtroubleshooting.model.troubleshooting.*;
import project.recommendationandtroubleshooting.model.troubleshooting.cep.CPUEvent;
import project.recommendationandtroubleshooting.model.troubleshooting.cep.Limit;
import project.recommendationandtroubleshooting.model.troubleshooting.cep.TemperatureEvent;
import project.recommendationandtroubleshooting.model.troubleshooting.cep.Warning;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class TestService {

    private final KieContainer kieContainer;

    @Autowired
    public TestService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public Problem testTroubleshooting() {

        Set<Description> desc1 = new HashSet<>();
        desc1.add(new Description("Računar ne može da se upali."));
        desc1.add(new Description("Računar ne reaguje na power dugme."));
        Map<Integer, Solution> sol1 = new HashMap<>();
        sol1.put(1, new Solution("Proveriti da li je računar uključen u struju i proveriti ispravnost power dugmeta."));
        sol1.put(2, new Solution("Odneti na dalju dijagnostiku u servis."));
        Bug bug1 = new Bug(desc1, sol1);

        Set<Description> desc2 = new HashSet<>();
        desc2.add(new Description("Računar ne može da se upali."));
        desc2.add(new Description("Računar ne prolazi POST."));
        Map<Integer, Solution> sol2 = new HashMap<>();
        sol2.put(1, new Solution("Neka periferija računara je blokirana, proveriti priključene uređaje."));
        sol2.put(2, new Solution("Izvaditi RAM iz memorije da bi se NV-RAM ponovo napunio i zatim ponovo resetovati računar."));
        sol2.put(3, new Solution("Neka komponenta računara crkava i daje loš napon, odneti na dijagnostiku u servis."));
        Bug bug2 = new Bug(desc2, sol2);

        Set<Description> desc3 = new HashSet<>();
        desc3.add(new Description("Računar ne može da se upali."));
        desc3.add(new Description("Računar ne reaguje na power dugme."));
        Problem problem = new Problem(desc3);
        problem.getTriedSolutions().add(new Solution("Proveriti da li je računar uključen u struju i proveriti ispravnost power dugmeta."));

        KieSession kieSession = kieContainer.newKieSession("troubleshootingSession");
        kieSession.insert(bug1);
        kieSession.insert(bug2);
        kieSession.insert(problem);
        kieSession.getAgenda().getAgendaGroup("troubleshooting").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
        return problem;
    }

    public Problem testTroubleshootingReport() {

        Set<Description> desc1 = new HashSet<>();
        desc1.add(new Description("Računar ne može da se upali."));
        desc1.add(new Description("Računar ne reaguje na power dugme."));
        Map<Integer, Solution> sol1 = new HashMap<>();
        sol1.put(1, new Solution("Proveriti da li je računar uključen u struju i proveriti ispravnost power dugmeta."));
        sol1.put(2, new Solution("Odneti na dalju dijagnostiku u servis."));
        Bug bug1 = new Bug(desc1, sol1);

        Set<Description> desc2 = new HashSet<>();
        desc2.add(new Description("Računar ne može da se upali."));
        desc2.add(new Description("Računar ne prolazi POST."));
        Map<Integer, Solution> sol2 = new HashMap<>();
        sol2.put(1, new Solution("Neka periferija računara je blokirana, proveriti priključene uređaje."));
        sol2.put(2, new Solution("Izvaditi RAM iz memorije da bi se NV-RAM ponovo napunio i zatim ponovo resetovati računar."));
        sol2.put(3, new Solution("Neka komponenta računara crkava i daje loš napon, odneti na dijagnostiku u servis."));
        Bug bug2 = new Bug(desc2, sol2);

        Set<Description> desc3 = new HashSet<>();
        desc3.add(new Description("Računar ne može da se upali."));
        desc3.add(new Description("Računar ne reaguje na power dugme."));
        Problem problem = new Problem(desc3);
        problem.getTriedSolutions().add(new Solution("Proveriti da li je računar uključen u struju i proveriti ispravnost power dugmeta."));


        User user = new User();
        user.setId(1L);

        Calendar today = Calendar.getInstance();
        Calendar nextYearToday = today;
        nextYearToday.add(Calendar.YEAR, -2);
        user.getBugHistory().add(new BugHistory(1L, new Date(), sol1.get(2), bug1));
        user.getBugHistory().add(new BugHistory(1L, new Date(), sol1.get(2), bug1));
        user.getBugHistory().add(new BugHistory(1L, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(1L, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(1L, new Date(), sol1.get(2), bug1));
        user.getBugHistory().add(new BugHistory(1L, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(1L, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(1L, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(1L, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(1L, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(2L, new Date(), sol2.get(3), bug2));
        user.getBugHistory().add(new BugHistory(2L, new Date(), sol2.get(3), bug2));
        user.getBugHistory().add(new BugHistory(2L, new Date(), sol2.get(3), bug2));
        user.getBugHistory().add(new BugHistory(2L, new Date(), sol2.get(2), bug2));
        user.getBugHistory().add(new BugHistory(2L, new Date(), sol2.get(2), bug2));


        KieSession kieSession = kieContainer.newKieSession("troubleshootingSession");
        kieSession.insert(bug1);
        kieSession.insert(bug2);
        kieSession.insert(problem);
        kieSession.insert(user);
        kieSession.insert(new Bugs());
        kieSession.setGlobal("userId", 1l);
        //kieSession.getAgenda().getAgendaGroup("unsolved_bugs").setFocus();
        kieSession.getAgenda().getAgendaGroup("bug_frequency").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
        return problem;
    }

    public void cpuEventTest() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSessionConfiguration conf = KieServices.Factory.get().newKieSessionConfiguration();
        conf.setOption(ClockTypeOption.get("pseudo"));
        KieSession kieSession = kieContainer.newKieSession("eventsPseudoSession", conf);

        Limit limit = new Limit(75L, LimitType.TEMPERATURE);
        kieSession.insert(limit);
        kieSession.getEntryPoint("entry-temp").insert(new TemperatureEvent(90L));

        SessionPseudoClock clock = kieSession.getSessionClock();
        clock.advanceTime(6, TimeUnit.MINUTES);
        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        kieSession.getEntryPoint("entry-temp").insert(new TemperatureEvent(60L));


        kieSession.getAgenda().getAgendaGroup("max_temperature").setFocus();
        long ruleFireCount = kieSession.fireAllRules();


        Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(Warning.class));

        kieSession.dispose();
    }
}
