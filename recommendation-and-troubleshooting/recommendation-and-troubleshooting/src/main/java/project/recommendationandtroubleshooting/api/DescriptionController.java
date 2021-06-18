package project.recommendationandtroubleshooting.api;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.recommendationandtroubleshooting.dto.DescriptionDTO;
import project.recommendationandtroubleshooting.mapper.DescriptionMapper;
import project.recommendationandtroubleshooting.model.troubleshooting.Description;
import project.recommendationandtroubleshooting.service.impl.DescriptionServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/descriptions", produces = MediaType.APPLICATION_JSON_VALUE)
public class DescriptionController {

    @Autowired
    DescriptionServiceImpl descriptionService;

    @Autowired
    KieSession kieSession;

    private final DescriptionMapper descriptionMapper;

    public DescriptionController() {
        descriptionMapper = new DescriptionMapper();
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created description."),
            @ApiResponse(code = 400, message = "Description already exists."),
    })
    @PostMapping()
    public ResponseEntity<DescriptionDTO> createDescription(@Valid @RequestBody DescriptionDTO descriptionDTO) {

        Description description = descriptionMapper.toEntity(descriptionDTO);

        Description savedDescription = descriptionService.saveOne(description);
        kieSession.insert(savedDescription);

        return new ResponseEntity<>(descriptionMapper.toDto(savedDescription), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted description with passed id."),
            @ApiResponse(code = 400, message = "Description with passed id can not be found."),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDescription(@PathVariable @Min(1) Integer id) {

        descriptionService.delete(id);

        Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle : handlers) {
            Object obj = kieSession.getObject(handle);

            if (obj.getClass() == Description.class) {
                if (((Description) obj).getId().equals(id))
                    kieSession.delete(handle);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated description with passed id."),
            @ApiResponse(code = 400, message = "Description with passed id can not be found."),
    })
    @PutMapping()
    public ResponseEntity<DescriptionDTO> updateDescription(@Valid @RequestBody DescriptionDTO descriptionDTO) {

        Description description = descriptionService.update(descriptionMapper.toEntity(descriptionDTO));

        Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle : handlers) {
            Object obj = kieSession.getObject(handle);

            if (obj.getClass() == Description.class) {
                if (((Description) obj).getId().equals(description.getId()))
                    kieSession.delete(handle);
            }
        }

        kieSession.insert(description);

        return new ResponseEntity<>(descriptionMapper.toDto(description), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved description with passed id."),
            @ApiResponse(code = 400, message = "Description with passed id can not be found."),
    })
    @GetMapping("/{id}")
    public ResponseEntity<DescriptionDTO> getDescription(@PathVariable @Min(1) Integer id) {

        Description description = descriptionService.findOne(id);
        return new ResponseEntity<>(descriptionMapper.toDto(description), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved descriptions page."),
    })
    @GetMapping("/by-page")
    public ResponseEntity<Page<DescriptionDTO>> getAllDescriptionsPage(Pageable pageable) {
        Page<Description> page = descriptionService.findAll(pageable);
        List<DescriptionDTO> descriptionDTOS = toDescriptionDTOList(page.toList());
        Page<DescriptionDTO> pageDescriptionDTOS = new PageImpl<>(descriptionDTOS, page.getPageable(), page.getTotalElements());

        return new ResponseEntity<>(pageDescriptionDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all descriptions."),
    })
    @GetMapping()
    public ResponseEntity<List<DescriptionDTO>> getAllDescription() {
        List<Description> descriptions = descriptionService.findAll();
        List<DescriptionDTO> descriptionDTOS = new ArrayList<>();
        for (Description description : descriptions) {
            descriptionDTOS.add(descriptionMapper.toDto(description));
        }
        return new ResponseEntity<>(descriptionDTOS, HttpStatus.OK);
    }

    private List<DescriptionDTO> toDescriptionDTOList(List<Description> descriptions) {
        List<DescriptionDTO> descriptionDTOs = new ArrayList<>();
        for (Description description : descriptions) {
            descriptionDTOs.add(descriptionMapper.toDto(description));
        }
        return descriptionDTOs;
    }
}
