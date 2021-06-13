package project.recommendationandtroubleshooting.api;
import java.util.Collection;

import javax.validation.Valid;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.recommendationandtroubleshooting.dto.FavoriteDTO;
import project.recommendationandtroubleshooting.dto.RatingDTO;
import project.recommendationandtroubleshooting.model.User;
import project.recommendationandtroubleshooting.service.impl.FavoriteServiceImpl;
import project.recommendationandtroubleshooting.service.impl.RatingServiceImpl;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/rating", produces = MediaType.APPLICATION_JSON_VALUE)
public class RatingController {
	
	@Autowired
    RatingServiceImpl ratingService;
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull input."),
            @ApiResponse(code = 400, message = "Invalid input."),
    })
    @PostMapping()
    public ResponseEntity<?> addRatng(@Valid @RequestBody RatingDTO dto) {
		ratingService.rate(dto);
        return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
}
	
