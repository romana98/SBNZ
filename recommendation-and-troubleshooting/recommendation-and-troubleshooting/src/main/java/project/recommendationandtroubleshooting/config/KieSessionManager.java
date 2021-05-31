package project.recommendationandtroubleshooting.config;

import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;

@Service
public class KieSessionManager {

    private HashSet<KieSession> kieSessions;

    @PostConstruct
    public void init() {
        this.kieSessions = new HashSet<>();
    }

    public void insert(KieSession kieSession) {
        this.kieSessions.add(kieSession);
    }

    public void delete(KieSession kieSession) {
        this.kieSessions.remove(kieSession);
    }
}