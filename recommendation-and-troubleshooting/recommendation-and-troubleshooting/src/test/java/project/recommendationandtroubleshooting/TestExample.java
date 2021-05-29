package project.recommendationandtroubleshooting;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestExample {
	
	@Test
    public void test() {
				
		//sintaksa za test
        /*KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("planner").setFocus();
        kieSession.insert(new UserHealth(1L, 65, 170, 70, 120, 79, 55, 0, new Date(), false));
        int num = kieSession.fireAllRules();

        assertEquals(14, num);

        assertTrue(userPlanner.isOld());
        assertEquals(24, userPlanner.getBmi(), 0.5);;*/
    }

}
