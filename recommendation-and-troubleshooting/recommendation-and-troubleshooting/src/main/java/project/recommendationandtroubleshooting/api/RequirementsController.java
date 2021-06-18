package project.recommendationandtroubleshooting.api;
import java.util.Collection;
import java.util.List;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.recommendationandtroubleshooting.dto.AddConfigurationDTO;
import project.recommendationandtroubleshooting.dto.BugDTO;
import project.recommendationandtroubleshooting.dto.ConfigurationResponseDTO;
import project.recommendationandtroubleshooting.dto.FavoriteDTO;
import project.recommendationandtroubleshooting.dto.IntervalDTO;
import project.recommendationandtroubleshooting.dto.RateDTO;
import project.recommendationandtroubleshooting.dto.RequirementResponseDTO;
import project.recommendationandtroubleshooting.dto.UsersByRateDTO;
import project.recommendationandtroubleshooting.model.Admin;
import project.recommendationandtroubleshooting.model.Person;
import project.recommendationandtroubleshooting.model.User;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationCharacteristicType;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationClass;
import project.recommendationandtroubleshooting.model.recommendation.ConfigurationUsageType;
import project.recommendationandtroubleshooting.model.recommendation.Configurations;
import project.recommendationandtroubleshooting.model.recommendation.InputRequirements;
import project.recommendationandtroubleshooting.model.recommendation.Recommendations;
import project.recommendationandtroubleshooting.model.troubleshooting.Bug;
import project.recommendationandtroubleshooting.service.RecommendationService;
import project.recommendationandtroubleshooting.service.impl.BugServiceImpl;
import project.recommendationandtroubleshooting.service.impl.ConfigurationCharacteristicTypeServiceImpl;
import project.recommendationandtroubleshooting.service.impl.ConfigurationServiceImpl;
import project.recommendationandtroubleshooting.service.impl.ConfigurationUsageTypeServiceImpl;
import project.recommendationandtroubleshooting.service.impl.FavoriteServiceImpl;
import project.recommendationandtroubleshooting.service.impl.RatingServiceImpl;
import project.recommendationandtroubleshooting.service.impl.RecommendationServiceImpl;
import project.recommendationandtroubleshooting.service.impl.RequirementsService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/requirements", produces = MediaType.APPLICATION_JSON_VALUE)
public class RequirementsController {
	
	@Autowired
	RequirementsService reqService;
	
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull input."),
            @ApiResponse(code = 400, message = "Invalid input."),
    })
    @GetMapping("/by-page")
    public ResponseEntity<Page<RequirementResponseDTO>> getAll(Pageable pageable) {
        return new ResponseEntity<Page<RequirementResponseDTO>>(reqService.getAll(pageable), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull input."),
            @ApiResponse(code = 400, message = "Invalid input."),
    })
    @PostMapping("/addUsage")
    public ResponseEntity<?> addUsage(@RequestBody RequirementResponseDTO dto) {
		if (reqService.changeUsageRequirement(dto, false)) {
	        return new ResponseEntity<List<RequirementResponseDTO>>(HttpStatus.OK);
		} else {
	        return new ResponseEntity<List<RequirementResponseDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull input."),
            @ApiResponse(code = 400, message = "Invalid input."),
    })
    @PostMapping("/addCharacteristic")
    public ResponseEntity<?> addCharacteristic(@RequestBody RequirementResponseDTO dto) {
		if (reqService.changeCharacteristicRequirement(dto, false)) {
	        return new ResponseEntity<List<RequirementResponseDTO>>(HttpStatus.OK);
		} else {
	        return new ResponseEntity<List<RequirementResponseDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull input."),
            @ApiResponse(code = 400, message = "Invalid input."),
    })
    @PostMapping("/deleteRequirement")
    public ResponseEntity<?> deleteRequirement(@RequestBody RequirementResponseDTO dto) {
		boolean success = dto.getType().equals("USAGE") ? reqService.changeUsageRequirement(dto, true) : reqService.changeCharacteristicRequirement(dto, true);
		if (success) {
	        return new ResponseEntity<List<RequirementResponseDTO>>(HttpStatus.OK);
		} else {
	        return new ResponseEntity<List<RequirementResponseDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

}
