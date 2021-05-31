package project.recommendationandtroubleshooting.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public interface KieService {

    KieContainer getKieContainer();

    KieSession getEventsSession();

    void setEventsSession(KieSession kieSession);

    KieSession getEventsPseudoSession();

    void setEventsPseudoSession(KieSession eventsPseudoSession);

    void releaseEventsSession();

    void releaseEventsPseudoSession();
}
