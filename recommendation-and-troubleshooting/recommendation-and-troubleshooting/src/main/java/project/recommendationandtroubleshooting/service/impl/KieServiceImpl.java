package project.recommendationandtroubleshooting.service.impl;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.service.KieService;

// u sustini necemo ovo koristiti nego cemo za event-ove praviti kie sesiju bas pri ucitavanju
// podataka iz fajla -> "na nivou request-a kie sesija"
@Service
public class KieServiceImpl implements KieService {

    @Autowired
    private KieContainer kieContainer;

    private KieSession eventsSession;
    private KieSession eventsPseudoSession;


    @Override
    public KieContainer getKieContainer() {
        return kieContainer;
    }


    @Override
    public KieSession getEventsSession() {
        if (eventsSession == null) {
            eventsSession = kieContainer.newKieSession("eventsSession");
        }
        return eventsSession;
    }

    @Override
    public void setEventsSession(KieSession kieSession) {
        this.eventsSession = kieSession;
    }

    @Override
    public KieSession getEventsPseudoSession() {
        if (eventsPseudoSession == null) {
            eventsPseudoSession = kieContainer.newKieSession("eventsPseudoSession");
        }
        return eventsPseudoSession;
    }

    @Override
    public void setEventsPseudoSession(KieSession eventsPseudoSession) {
        this.eventsPseudoSession = eventsPseudoSession;
    }

    @Override
    public void releaseEventsSession() {
        this.eventsSession.dispose();
        this.eventsSession = null;
    }

    @Override
    public void releaseEventsPseudoSession() {
        this.eventsPseudoSession.dispose();
        this.eventsPseudoSession = null;
    }
}
