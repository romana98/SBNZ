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
import project.recommendationandtroubleshooting.dto.UsersByRateDTO;
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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/recommendation", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecommendationController {
	
	@Autowired
    FavoriteServiceImpl favoriteService;
	
	@Autowired
    RatingServiceImpl ratingService;
	
	@Autowired
    RecommendationServiceImpl recommendationService;
	
	@Autowired
	ConfigurationServiceImpl configurationService;
	
	
	@Autowired
	ConfigurationUsageTypeServiceImpl usagesService;
	
	@Autowired
	ConfigurationCharacteristicTypeServiceImpl characteristicsService;
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull input."),
            @ApiResponse(code = 400, message = "Invalid input."),
    })
    @PostMapping("/by-page")
    public ResponseEntity<Page<ConfigurationResponseDTO>> recommend(@Valid @RequestBody InputRequirements input, Pageable pageable) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User person = (User) authentication.getPrincipal();
		Page<ConfigurationResponseDTO> output = recommendationService.getRecommendation(input, pageable, person.getId());
        return new ResponseEntity<Page<ConfigurationResponseDTO>>(output, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull input."),
            @ApiResponse(code = 400, message = "Invalid input."),
    })
    @GetMapping("/getCurrentlyPopular/by-page")
    public ResponseEntity<Page<ConfigurationResponseDTO>> getCurrentlyPopular(Pageable pageable) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User person = (User) authentication.getPrincipal();
		Page<ConfigurationResponseDTO> output = recommendationService.getCurrentlyPopular(pageable, person.getId());
        return new ResponseEntity<Page<ConfigurationResponseDTO>>(output, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull input."),
            @ApiResponse(code = 400, message = "Invalid input."),
    })
    @PostMapping("/getIntervalPopular/by-page")
    public ResponseEntity<Page<ConfigurationResponseDTO>> getIntervalPopular(@Valid @RequestBody IntervalDTO dto, Pageable pageable) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User person = (User) authentication.getPrincipal();
		Page<ConfigurationResponseDTO> output = recommendationService.getIntervalPopular(dto, pageable, person.getId());
        return new ResponseEntity<Page<ConfigurationResponseDTO>>(output, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull input."),
            @ApiResponse(code = 400, message = "Invalid input."),
    })
    @PostMapping("/searchByRate/by-page")
    public ResponseEntity<Page<ConfigurationResponseDTO>> searchByRate(@Valid @RequestBody RateDTO dto, Pageable pageable) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User person = (User) authentication.getPrincipal();
		Page<ConfigurationResponseDTO> output = recommendationService.searchByRate(dto, pageable, person.getId());
        return new ResponseEntity<Page<ConfigurationResponseDTO>>(output, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull input."),
            @ApiResponse(code = 400, message = "Invalid input."),
    })
    @GetMapping("/getAverageRating")
    public ResponseEntity<Double> getAverageRating(@RequestParam Long configurationId) {

		Double output = recommendationService.getAverageRating(configurationId);
        return new ResponseEntity<Double>(output, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull input."),
            @ApiResponse(code = 400, message = "Invalid input."),
    })
    @GetMapping("all/by-page")
    public ResponseEntity<Page<ConfigurationResponseDTO>> getAll(Pageable pageable) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User person = (User) authentication.getPrincipal();
		Page<ConfigurationResponseDTO> output = configurationService.findAll(pageable, person.getId());
        return new ResponseEntity<Page<ConfigurationResponseDTO>>(output, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull input."),
            @ApiResponse(code = 400, message = "Invalid input."),
    })
    @GetMapping("favorites/by-page")
    public ResponseEntity<Page<ConfigurationResponseDTO>> getFavoritesByUser(Pageable pageable) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User person = (User) authentication.getPrincipal();
		Page<ConfigurationResponseDTO> output = configurationService.getFavoritesByUser(pageable, person.getId());
        return new ResponseEntity<Page<ConfigurationResponseDTO>>(output, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull input."),
            @ApiResponse(code = 400, message = "Invalid input."),
    })
    @PostMapping("add-configuration")
    public ResponseEntity<ConfigurationClass> addConfiguration(@Valid @RequestBody AddConfigurationDTO dto) {
		ConfigurationClass output = configurationService.addConfiguration(dto);
        return new ResponseEntity<ConfigurationClass>(output, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull input."),
            @ApiResponse(code = 400, message = "Invalid input."),
    })
    @GetMapping("delete-configuration/{id}")
    public ResponseEntity<?> deleteConfiguration(@PathVariable("id") int id) {
		configurationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull input."),
            @ApiResponse(code = 400, message = "Invalid input."),
    })
    @GetMapping("get-users-by-rate/{id}")
    public ResponseEntity<UsersByRateDTO> getUsersByRate(@PathVariable("id") int configurationId) {
		Long ones = recommendationService.getUsersByRate((long)configurationId, 1L);
		Long twos = recommendationService.getUsersByRate((long)configurationId, 2L);
		Long threes = recommendationService.getUsersByRate((long)configurationId, 3L);
		Long fours = recommendationService.getUsersByRate((long)configurationId, 4L);
		Long fives = recommendationService.getUsersByRate((long)configurationId, 5L);
        return new ResponseEntity<>(new UsersByRateDTO(ones, twos, threes, fours, fives), HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull input."),
            @ApiResponse(code = 400, message = "Invalid input."),
    })
    @GetMapping("/getUsages")
    public ResponseEntity<List<ConfigurationUsageType>> getUsages() {
        return new ResponseEntity<List<ConfigurationUsageType>>(usagesService.findAll(), HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfull input."),
            @ApiResponse(code = 400, message = "Invalid input."),
    })
    @GetMapping("/getCharacteristics")
    public ResponseEntity<List<ConfigurationCharacteristicType>> getCharacteristics() {
        return new ResponseEntity<List<ConfigurationCharacteristicType>>(characteristicsService.findAll(), HttpStatus.OK);
	}
	
}
	
