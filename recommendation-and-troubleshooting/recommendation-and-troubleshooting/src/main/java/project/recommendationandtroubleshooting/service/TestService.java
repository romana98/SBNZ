package project.recommendationandtroubleshooting.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.model.TestModel;

@Service
public class TestService {

    private final KieContainer kieContainer;

    @Autowired
    public TestService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public TestModel getResponse(TestModel model) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(model);
        kieSession.fireAllRules();
        kieSession.dispose();
        return model;
    }
}
