package project.recommendationandtroubleshooting.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.recommendationandtroubleshooting.model.TestModel;
import project.recommendationandtroubleshooting.model.recommendation.Output;
import project.recommendationandtroubleshooting.service.RecommendationService;
import project.recommendationandtroubleshooting.service.TestService;

@RestController
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @RequestMapping(value = "/getRecommendation", method = RequestMethod.GET, produces = "application/json")
    public Output getResponse() {

    	recommendationService.getRecommendation();
        return null;
    }
}
