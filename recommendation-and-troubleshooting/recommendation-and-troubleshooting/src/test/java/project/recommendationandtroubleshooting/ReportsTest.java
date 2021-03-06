package project.recommendationandtroubleshooting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.recommendationandtroubleshooting.enums.ConfigurationType;
import project.recommendationandtroubleshooting.enums.DiscType;
import project.recommendationandtroubleshooting.model.User;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationClass;
import project.recommendationandtroubleshooting.model.recommendation.Configurations;
import project.recommendationandtroubleshooting.model.recommendation.Favorite;
import project.recommendationandtroubleshooting.model.troubleshooting.*;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportsTest {

    @Test
    public void testRecommendationReportRule() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("rulesSession");

        ConfigurationClass c1 = new ConfigurationClass(1, 52999L, ConfigurationType.LAPTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "8GB DDR4 2666 MHz", "Windows 10 Pro 64bit", "500W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "13", "1024 x 768", "musicCard1", true, true, true, false, null, true);
        ConfigurationClass c2 = new ConfigurationClass(2, 99999L, ConfigurationType.DESKTOP, "Intel Core i3 Processor", "GeForce GTX 1050 Ti", "16GB DDR4 2400 MHz", "Windows 10 Pro 64bit", "600W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "15", "1024 x 768", "musicCard1", false, true, true, true, null, true);
        ConfigurationClass c3 = new ConfigurationClass(3, 89999L, ConfigurationType.DESKTOP, "AMD Ryzen 5", "ASUS GeForce GTX 1050 Ti Cerberus OC 4GB GDDR5 128bit - CERBERUS-GTX1050TI-O4G", "16GB DDR4 2400 MHz", "Windows 10 Pro 64bit", "600W", DiscType.SSD, "240GB", "MSI H3110M PRO-M2 PLUS", "15", "3840 x 1440", "musicCard1", false, true, true, false, null, true);

        kieSession.insert(c1);
        kieSession.insert(c2);
        kieSession.insert(c3);

        User u1 = new User();
        u1.getFavorites().add(new Favorite(c1, 1L, new Date()));
        u1.getFavorites().add(new Favorite(c2, 1L, new Date()));

        User u2 = new User();
        u2.getFavorites().add(new Favorite(c1, 1L, new Date()));
        u2.getFavorites().add(new Favorite(c2, 1L, new Date()));

        User u3 = new User();
        u3.getFavorites().add(new Favorite(c1, 1L, new Date()));
        u3.getFavorites().add(new Favorite(c3, 1L, new Date()));

        User u4 = new User();
        u4.getFavorites().add(new Favorite(c1, 1L, new Date()));
        u4.getFavorites().add(new Favorite(c2, 1L, new Date()));

        User u5 = new User();
        u5.getFavorites().add(new Favorite(c1, 1L, new Date()));
        u5.getFavorites().add(new Favorite(c2, 1L, new Date()));

        User u6 = new User();
        u6.getFavorites().add(new Favorite(c1, 1L, new Date()));
        u6.getFavorites().add(new Favorite(c2, 1L, new Date()));


        Configurations output = new Configurations();

        kieSession.insert(output);
        kieSession.insert(u1);
        kieSession.insert(u2);
        kieSession.insert(u3);
        kieSession.insert(u4);
        kieSession.insert(u5);
        kieSession.insert(u6);

        kieSession.getAgenda().getAgendaGroup("currently_popular").setFocus();
        kieSession.fireAllRules();

        assertEquals(2, output.getConfigurations().size());

        kieSession.dispose();
    }


    @Test
    public void testTroubleshootingReportRuleBugFrequency() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("rulesSession");

        Set<Description> desc1 = new HashSet<>();
        desc1.add(new Description("Ra??unar ne mo??e da se upali."));
        desc1.add(new Description("Ra??unar ne reaguje na power dugme."));
        Map<Integer, Solution> sol1 = new HashMap<>();
        sol1.put(1, new Solution("Proveriti da li je ra??unar uklju??en u struju i proveriti ispravnost power dugmeta."));
        sol1.put(2, new Solution("Odneti na dalju dijagnostiku u servis."));
        Bug bug1 = new Bug(desc1, sol1);

        Set<Description> desc2 = new HashSet<>();
        desc2.add(new Description("Ra??unar ne mo??e da se upali."));
        desc2.add(new Description("Ra??unar ne prolazi POST."));
        Map<Integer, Solution> sol2 = new HashMap<>();
        sol2.put(1, new Solution("Neka periferija ra??unara je blokirana, proveriti priklju??ene ure??aje."));
        sol2.put(2, new Solution("Izvaditi RAM iz memorije da bi se NV-RAM ponovo napunio i zatim ponovo resetovati ra??unar."));
        sol2.put(3, new Solution("Neka komponenta ra??unara crkava i daje lo?? napon, odneti na dijagnostiku u servis."));
        Bug bug2 = new Bug(desc2, sol2);

        Set<Description> desc3 = new HashSet<>();
        desc3.add(new Description("Ra??unar ne mo??e da se upali."));
        desc3.add(new Description("Ra??unar ne reaguje na power dugme."));
        Problem problem = new Problem(desc3);
        problem.getTriedSolutions().add(new Solution("Proveriti da li je ra??unar uklju??en u struju i proveriti ispravnost power dugmeta."));


        User user = new User();
        user.setId(1);

        Calendar today = Calendar.getInstance();
        Calendar nextYearToday = today;
        nextYearToday.add(Calendar.YEAR, -2);
        user.getBugHistory().add(new BugHistory(1, new Date(), sol1.get(2), bug1));
        user.getBugHistory().add(new BugHistory(1, new Date(), sol1.get(2), bug1));
        user.getBugHistory().add(new BugHistory(1, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(1, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(1, new Date(), sol1.get(2), bug1));
        user.getBugHistory().add(new BugHistory(1, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(1, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(1, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(1, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(1, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(2, new Date(), sol2.get(3), bug2));
        user.getBugHistory().add(new BugHistory(2, new Date(), sol2.get(3), bug2));
        user.getBugHistory().add(new BugHistory(2, new Date(), sol2.get(3), bug2));
        user.getBugHistory().add(new BugHistory(2, new Date(), sol2.get(2), bug2));
        user.getBugHistory().add(new BugHistory(2, new Date(), sol2.get(2), bug2));

        Bugs output = new Bugs();

        kieSession.insert(bug1);
        kieSession.insert(bug2);
        kieSession.insert(problem);
        kieSession.insert(user);
        kieSession.insert(output);
        kieSession.setGlobal("userId", 1l);
        kieSession.getAgenda().getAgendaGroup("bug_frequency").setFocus();
        kieSession.fireAllRules();

        assertEquals(output.getBugs().size(), 1);

        kieSession.dispose();
    }


    @Test
    public void testTroubleshootingReportRuleUnsolvedBugs() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("rulesSession");

        Set<Description> desc1 = new HashSet<>();
        desc1.add(new Description("Ra??unar ne mo??e da se upali."));
        desc1.add(new Description("Ra??unar ne reaguje na power dugme."));
        Map<Integer, Solution> sol1 = new HashMap<>();
        sol1.put(1, new Solution("Proveriti da li je ra??unar uklju??en u struju i proveriti ispravnost power dugmeta."));
        sol1.put(2, new Solution("Odneti na dalju dijagnostiku u servis."));
        Bug bug1 = new Bug(desc1, sol1);

        Set<Description> desc2 = new HashSet<>();
        desc2.add(new Description("Ra??unar ne mo??e da se upali."));
        desc2.add(new Description("Ra??unar ne prolazi POST."));
        Map<Integer, Solution> sol2 = new HashMap<>();
        sol2.put(1, new Solution("Neka periferija ra??unara je blokirana, proveriti priklju??ene ure??aje."));
        sol2.put(2, new Solution("Izvaditi RAM iz memorije da bi se NV-RAM ponovo napunio i zatim ponovo resetovati ra??unar."));
        sol2.put(3, new Solution("Neka komponenta ra??unara crkava i daje lo?? napon, odneti na dijagnostiku u servis."));
        Bug bug2 = new Bug(desc2, sol2);

        Set<Description> desc3 = new HashSet<>();
        desc3.add(new Description("Ra??unar ne mo??e da se upali."));
        desc3.add(new Description("Ra??unar ne reaguje na power dugme."));
        Problem problem = new Problem(desc3);
        problem.getTriedSolutions().add(new Solution("Proveriti da li je ra??unar uklju??en u struju i proveriti ispravnost power dugmeta."));


        User user = new User();
        user.setId(1);

        Calendar today = Calendar.getInstance();
        Calendar nextYearToday = today;
        nextYearToday.add(Calendar.YEAR, -2);
        user.getBugHistory().add(new BugHistory(1, new Date(), sol1.get(2), bug1));
        user.getBugHistory().add(new BugHistory(1, new Date(), sol1.get(2), bug1));
        user.getBugHistory().add(new BugHistory(1, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(1, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(1, new Date(), sol1.get(2), bug1));
        user.getBugHistory().add(new BugHistory(1, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(1, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(1, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(1, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(1, new Date(), sol1.get(1), bug1));
        user.getBugHistory().add(new BugHistory(2, new Date(), sol2.get(3), bug2));
        user.getBugHistory().add(new BugHistory(2, new Date(), sol2.get(3), bug2));
        user.getBugHistory().add(new BugHistory(2, new Date(), sol2.get(3), bug2));
        user.getBugHistory().add(new BugHistory(2, new Date(), sol2.get(2), bug2));
        user.getBugHistory().add(new BugHistory(2, new Date(), sol2.get(2), bug2));

        Bugs output = new Bugs();

        kieSession.insert(bug1);
        kieSession.insert(bug2);
        kieSession.insert(problem);
        kieSession.insert(user);
        kieSession.insert(output);
        kieSession.setGlobal("userId", 1l);
        kieSession.getAgenda().getAgendaGroup("unsolved_bugs").setFocus();
        kieSession.fireAllRules();

        assertEquals(output.getBugs().size(), 2);

        kieSession.dispose();
    }


}