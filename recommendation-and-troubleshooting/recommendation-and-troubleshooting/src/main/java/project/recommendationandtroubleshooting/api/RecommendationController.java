package project.recommendationandtroubleshooting.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.recommendationandtroubleshooting.dto.BugDTO;
import project.recommendationandtroubleshooting.model.recommendation.Configurations;
import project.recommendationandtroubleshooting.model.recommendation.InputRequirements;
import project.recommendationandtroubleshooting.model.recommendation.Recommendations;
import project.recommendationandtroubleshooting.model.troubleshooting.Bug;
import project.recommendationandtroubleshooting.service.RecommendationService;
import project.recommendationandtroubleshooting.service.impl.BugServiceImpl;
import project.recommendationandtroubleshooting.service.impl.FavoriteServiceImpl;
import project.recommendationandtroubleshooting.service.impl.RatingServiceImpl;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/recommendation", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecommendationController {
	
	@Autowired
    FavoriteServiceImpl favoriteService;
	
	@Autowired
    RatingServiceImpl ratingService;
	
	@PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull input."),
            @ApiResponse(code = 400, message = "Invalid input."),
    })
    @PostMapping()
    public ResponseEntity<BugDTO> createBug(@Valid @RequestBody InputRequirements input) {

		System.out.println(input);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
/*
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
    
    @RequestMapping(value = "/getIntervalPopular", method = RequestMethod.GET, produces = "application/json")
    public Configurations getIntervalPopular() {

        recommendationService.getIntervalPopular();
        return null;
    }*/
}
