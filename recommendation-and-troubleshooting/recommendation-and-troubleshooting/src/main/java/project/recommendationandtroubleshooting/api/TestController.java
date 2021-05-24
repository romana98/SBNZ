package project.recommendationandtroubleshooting.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.recommendationandtroubleshooting.model.TestModel;
import project.recommendationandtroubleshooting.model.troubleshooting.Problem;
import project.recommendationandtroubleshooting.service.TestService;

@RestController
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(value = "/getResponse", method = RequestMethod.GET, produces = "application/json")
    public TestModel getResponse(@RequestParam String message) {

        TestModel model = new TestModel();
        model.setIncomeMessage(message);
        testService.getResponse(model);
        return model;
    }

    @RequestMapping(value = "/testTroubleshooting", method = RequestMethod.GET, produces = "application/json")
    public Problem testTroubleshooting() {

        return testService.testTroubleshooting();
    }
}
