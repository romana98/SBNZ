package project.recommendationandtroubleshooting;

import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.recommendationandtroubleshooting.enums.LimitType;
import project.recommendationandtroubleshooting.model.troubleshooting.cep.*;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CEPTest {

    @Test
    public void cpuEventTest() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("eventsSession");

        Limit limit = new Limit(50L, LimitType.CPU);
        kieSession.insert(limit);
        for (int i = 0; i < 6; i++) {
            kieSession.insert(new CPUEvent(61L));
            /*try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

        }
        kieSession.getAgenda().getAgendaGroup("max_cpu_usage").setFocus();
        long ruleFireCount = kieSession.fireAllRules();
        assertEquals(1L, ruleFireCount);

        Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(Warning.class));
        assertThat(newEvents.size(), equalTo(1));

        kieSession.dispose();
    }

    @Test
    public void temperatureEventTest() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("eventsSession");

        Limit limit = new Limit(75L, LimitType.TEMPERATURE);
        kieSession.insert(limit);
        kieSession.getEntryPoint("entry-temp").insert(new TemperatureEvent(90L));


        kieSession.getAgenda().getAgendaGroup("max_temperature").setFocus();
        long ruleFireCount = kieSession.fireAllRules();
        assertEquals(1L, ruleFireCount);

        Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(Warning.class));
        assertThat(newEvents.size(), equalTo(1));

        kieSession.dispose();
    }

    @Test
    public void temperatureEventPseudoTest() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        /*KieSessionConfiguration conf = KieServices.Factory.get().newKieSessionConfiguration();
        conf.setOption( ClockTypeOption.get("pseudo"));*/
        KieSession kieSession = kieContainer.newKieSession("eventsPseudoSession");
        SessionPseudoClock clock = kieSession.getSessionClock();

        Limit limit = new Limit(75L, LimitType.TEMPERATURE);
        kieSession.insert(limit);
        kieSession.getEntryPoint("entry-temp").insert(new TemperatureEvent(90L));


        clock.advanceTime(6, TimeUnit.MINUTES);
        kieSession.getEntryPoint("entry-temp").insert(new TemperatureEvent(60L));


        kieSession.getAgenda().getAgendaGroup("max_temperature").setFocus();
        long ruleFireCount = kieSession.fireAllRules();
        assertEquals(1L, ruleFireCount);

        Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(Warning.class));
        assertThat(newEvents.size(), equalTo(1));

        kieSession.dispose();
    }

    @Test
    public void ramEventTest() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("eventsSession");

        Limit limit = new Limit(75L, LimitType.RAM);
        kieSession.insert(limit);
        kieSession.getEntryPoint("entry-ram").insert(new RAMEvent(90L));


        kieSession.getAgenda().getAgendaGroup("max_ram").setFocus();
        long ruleFireCount = kieSession.fireAllRules();
        assertEquals(1L, ruleFireCount);

        Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(Warning.class));
        assertThat(newEvents.size(), equalTo(1));

        kieSession.dispose();
    }

    @Test
    public void ramEventPseudoTest() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        /*KieSessionConfiguration conf = KieServices.Factory.get().newKieSessionConfiguration();
        conf.setOption( ClockTypeOption.get("pseudo"));*/
        KieSession kieSession = kieContainer.newKieSession("eventsPseudoSession");
        SessionPseudoClock clock = kieSession.getSessionClock();

        Limit limit = new Limit(75L, LimitType.RAM);
        kieSession.insert(limit);
        kieSession.getEntryPoint("entry-ram").insert(new RAMEvent(90L));

        clock.advanceTime(2, TimeUnit.MINUTES);
        kieSession.getEntryPoint("entry-ram").insert(new RAMEvent(60L));


        kieSession.getAgenda().getAgendaGroup("max_ram").setFocus();
        long ruleFireCount = kieSession.fireAllRules();
        assertEquals(1L, ruleFireCount);

        Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(Warning.class));
        assertThat(newEvents.size(), equalTo(1));

        kieSession.dispose();
    }

    @Test
    public void discEventTest() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("eventsSession");

        Limit limit = new Limit(75L, LimitType.DISC);
        kieSession.insert(limit);
        kieSession.getEntryPoint("entry-disc").insert(new DiscEvent(90L));


        kieSession.getAgenda().getAgendaGroup("max_disc").setFocus();
        long ruleFireCount = kieSession.fireAllRules();
        assertEquals(1L, ruleFireCount);

        Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(Warning.class));
        assertThat(newEvents.size(), equalTo(1));

        kieSession.dispose();
    }

    @Test
    public void discEventPseudoTest() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
       /* KieSessionConfiguration conf = KieServices.Factory.get().newKieSessionConfiguration();
        conf.setOption( ClockTypeOption.get("pseudo"));*/
        KieSession kieSession = kieContainer.newKieSession("eventsPseudoSession");
        SessionPseudoClock clock = kieSession.getSessionClock();

        Limit limit = new Limit(75L, LimitType.DISC);
        kieSession.insert(limit);
        kieSession.getEntryPoint("entry-disc").insert(new DiscEvent(90L));

        clock.advanceTime(2, TimeUnit.HOURS);
        kieSession.getEntryPoint("entry-disc").insert(new DiscEvent(60L));


        kieSession.getAgenda().getAgendaGroup("max_disc").setFocus();
        long ruleFireCount = kieSession.fireAllRules();
        assertEquals(1L, ruleFireCount);

        Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(Warning.class));
        assertThat(newEvents.size(), equalTo(1));

        kieSession.dispose();
    }

    @Test
    public void discEventAvgTest() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("eventsPseudoSession");
        SessionPseudoClock clock = kieSession.getSessionClock();

        Limit limit = new Limit(75L, LimitType.DISC);
        kieSession.insert(limit);
        kieSession.insert(new DiscEvent(70L));
        clock.advanceTime(2, TimeUnit.HOURS);
        kieSession.insert(new DiscEvent(80L));
        clock.advanceTime(2, TimeUnit.HOURS);
        kieSession.insert(new DiscEvent(90L));


        kieSession.getAgenda().getAgendaGroup("avg_disc").setFocus();
        long ruleFireCount = kieSession.fireAllRules();
        assertEquals(1L, ruleFireCount);

        Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(Warning.class));
        assertThat(newEvents.size(), equalTo(1));

        kieSession.dispose();
    }

}
