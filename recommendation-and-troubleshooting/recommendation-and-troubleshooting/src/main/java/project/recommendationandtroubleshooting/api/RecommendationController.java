package project.recommendationandtroubleshooting.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import project.recommendationandtroubleshooting.model.recommendation.Configurations;
import project.recommendationandtroubleshooting.model.recommendation.Recommendations;
import project.recommendationandtroubleshooting.service.RecommendationService;

@RestController
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @RequestMapping(value = "/getRecommendation", method = RequestMethod.GET, produces = "application/json")
    public Recommendations getResponse() {

        recommendationService.getRecommendation();
        return null;
    }

    @RequestMapping(value = "/getCurrentlyPopular", method = RequestMethod.GET, produces = "application/json")
    public Configurations getCurrentlyPopular() {

        recommendationService.getCurrentlyPopular();
        return null;
    }
}
