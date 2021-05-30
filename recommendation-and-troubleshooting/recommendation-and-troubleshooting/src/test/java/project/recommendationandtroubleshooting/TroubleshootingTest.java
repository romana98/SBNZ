package project.recommendationandtroubleshooting;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import project.recommendationandtroubleshooting.enums.ConfigurationType;
import project.recommendationandtroubleshooting.enums.DiscType;
import project.recommendationandtroubleshooting.model.recommendation.Budget;
import project.recommendationandtroubleshooting.model.recommendation.Configuration;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationCharacteristicType;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationCharacteristicTypeRequirements;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationUsageType;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationUsageTypeRequirements;
import project.recommendationandtroubleshooting.model.recommendation.InputRequirements;
import project.recommendationandtroubleshooting.model.recommendation.Mobility;
import project.recommendationandtroubleshooting.model.recommendation.Recommendations;
import project.recommendationandtroubleshooting.model.troubleshooting.Bug;
import project.recommendationandtroubleshooting.model.troubleshooting.Description;
import project.recommendationandtroubleshooting.model.troubleshooting.Problem;
import project.recommendationandtroubleshooting.model.troubleshooting.Solution;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TroubleshootingTest {

	@Test
	public void testTroubleshootingRule() {
		KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup("troubleshooting").setFocus();
        
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

        kieSession.insert(bug1);
        kieSession.insert(bug2);
        kieSession.insert(problem);
        
        kieSession.fireAllRules();
        
        assertEquals(problem.getCurrentSolution().getSolution(), "Odneti na dalju dijagnostiku u servis.");
        
        kieSession.dispose();
	}
}