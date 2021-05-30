package project.recommendationandtroubleshooting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.recommendationandtroubleshooting.enums.ConfigurationType;
import project.recommendationandtroubleshooting.enums.DiscType;
import project.recommendationandtroubleshooting.model.recommendation.Configuration;
import project.recommendationandtroubleshooting.model.recommendation.Rating;
import project.recommendationandtroubleshooting.model.troubleshooting.cep.CPUEvent;

import java.util.HashSet;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CEPTest {
	
	@Test
    public void cpuEventTest() {
		KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
		KieSession kieSession = kieContainer.newKieSession("eventsSession");
        for (int i = 0; i < 6; i++){
            kieSession.insert(new CPUEvent(10L, 61L));
            /*try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

        }
        kieSession.getAgenda().getAgendaGroup("max_cpu_usage").setFocus();
        long ruleFireCount = kieSession.fireAllRules();
        assertEquals(1L, ruleFireCount);
        
        kieSession.dispose();
	}

}
