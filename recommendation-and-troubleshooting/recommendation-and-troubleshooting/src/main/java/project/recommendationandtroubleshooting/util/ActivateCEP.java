package project.recommendationandtroubleshooting.util;

import org.drools.core.time.SessionPseudoClock;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import project.recommendationandtroubleshooting.enums.LimitType;
import project.recommendationandtroubleshooting.model.troubleshooting.cep.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class ActivateCEP {

    public static List<Warning> activate() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kSession = kieContainer.newKieSession("eventsPseudoSession");
        SessionPseudoClock clock = kSession.getSessionClock();


        Random generator = new Random(System.currentTimeMillis());

        discEventAvgTest(kSession, clock, generator);
        discEvent(kSession, clock, generator);
        temperatureEvent(kSession, clock, generator);
        ramEvent(kSession, clock, generator);
        cpuEvent(kSession, clock, generator);



        kSession.getAgenda().getAgendaGroup("events").setFocus();
        kSession.fireAllRules();

        List<Warning> warnings = new ArrayList<>();
        Collection<Warning> newEvents = (Collection<Warning>) kSession.getObjects(new ClassObjectFilter(Warning.class));
        for (int i = 0; i < newEvents.toArray().length; i++) {
            warnings.add((Warning) newEvents.toArray()[i]);
        }

        Collection<FactHandle> handlers = kSession.getFactHandles();
        for (FactHandle handle : handlers) {
            Object obj = kSession.getObject(handle);

            if (obj.getClass() == Warning.class)
                kSession.delete(handle);
        }

        return filterWarningList(warnings);
    }

    private static void cpuEvent(KieSession kSession, SessionPseudoClock clock, Random generator) {
        Limit limit = new Limit(65L, LimitType.CPU);
        kSession.insert(limit);

        for (int i = 0; i < 10; i++) {
            kSession.insert(new CPUEvent(getRandomLong(10, 201, generator)));
            clock.advanceTime(getRandomInt(0, 1, generator), TimeUnit.SECONDS);
        }
    }

    private static void temperatureEvent(KieSession kSession, SessionPseudoClock clock, Random generator) {
        Limit limit = new Limit(65L, LimitType.TEMPERATURE);
        kSession.insert(limit);

        for (int i = 0; i < 10; i++) {
            kSession.getEntryPoint("entry-temp").insert(new TemperatureEvent(getRandomLong(40, 201, generator)));
            clock.advanceTime(getRandomInt(1, 10, generator), TimeUnit.MINUTES);
        }
    }

    private static void ramEvent(KieSession kSession, SessionPseudoClock clock, Random generator) {
        Limit limit = new Limit(65L, LimitType.RAM);
        kSession.insert(limit);

        for (int i = 0; i < 10; i++) {
            kSession.getEntryPoint("entry-ram").insert(new RAMEvent(getRandomLong(30, 201, generator)));
            clock.advanceTime(getRandomInt(1, 90, generator), TimeUnit.SECONDS);
        }
    }

    private static void discEvent(KieSession kSession, SessionPseudoClock clock,  Random generator) {
        Limit limit = new Limit(75L, LimitType.DISC);
        kSession.insert(limit);

        for (int i = 0; i < 10; i++) {
            kSession.getEntryPoint("entry-disc").insert(new DiscEvent(getRandomLong(10, 101, generator)));
            clock.advanceTime(getRandomInt(1, 120, generator), TimeUnit.MINUTES);
        }
    }

    private static void discEventAvgTest(KieSession kSession, SessionPseudoClock clock, Random generator) {
        for (int i = 0; i < 10; i++) {
            kSession.insert(new DiscEvent(getRandomLong(40, 201, generator)));
            clock.advanceTime(getRandomInt(0, 2, generator), TimeUnit.HOURS);
        }
    }

    private static long getRandomLong(int min, int max, Random generator) {

        return (long) ((generator.nextDouble() * (max - min)) + min);
    }

    private static int getRandomInt(int min, int max, Random generator) {
        return (int) ((generator.nextDouble() * (max - min)) + min);
    }

    private static List<Warning> filterWarningList(List<Warning> warningList) {
        List<Warning> warnings = new ArrayList<>();
        List<String> alreadyThere = new ArrayList<>();
        for (Warning warning : warningList) {
            if (!alreadyThere.contains(warning.getWarning())) {
                warnings.add(warning);
                alreadyThere.add(warning.getWarning());
            }
        }
        return warnings;
    }
}
