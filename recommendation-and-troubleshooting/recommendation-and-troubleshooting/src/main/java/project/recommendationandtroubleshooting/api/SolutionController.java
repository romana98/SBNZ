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
import project.recommendationandtroubleshooting.dto.SolutionDTO;
import project.recommendationandtroubleshooting.mapper.SolutionMapper;
import project.recommendationandtroubleshooting.model.troubleshooting.Solution;
import project.recommendationandtroubleshooting.service.impl.SolutionServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/solutions", produces = MediaType.APPLICATION_JSON_VALUE)
public class SolutionController {

    @Autowired
    SolutionServiceImpl solutionService;

    @Autowired
    KieSession kieSession;


    private final SolutionMapper solutionMapper;

    public SolutionController() {
        solutionMapper = new SolutionMapper();
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created solution."),
            @ApiResponse(code = 400, message = "Solution already exists."),
    })
    @PostMapping()
    public ResponseEntity<SolutionDTO> createSolution(@Valid @RequestBody SolutionDTO solutionDTO) {

        Solution solution = solutionMapper.toEntity(solutionDTO);

        Solution savedSolution = solutionService.saveOne(solution);
        kieSession.insert(savedSolution);

        return new ResponseEntity<>(solutionMapper.toDto(savedSolution), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted solution with passed id."),
            @ApiResponse(code = 400, message = "Solution with passed id can not be found."),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSolution(@PathVariable @Min(1) Integer id) {

        solutionService.delete(id);

        Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle : handlers) {
            Object obj = kieSession.getObject(handle);

            if (obj.getClass() == Solution.class) {
                if (((Solution) obj).getId().equals(id))
                    kieSession.delete(handle);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated solution with passed id."),
            @ApiResponse(code = 400, message = "Solution with passed id can not be found."),
    })
    @PutMapping()
    public ResponseEntity<SolutionDTO> updateSolution(@Valid @RequestBody SolutionDTO solutionDTO) {

        Solution solution = solutionService.update(solutionMapper.toEntity(solutionDTO));

        Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle : handlers) {
            Object obj = kieSession.getObject(handle);

            if (obj.getClass() == Solution.class) {
                if (((Solution) obj).getId().equals(solution.getId()))
                    kieSession.delete(handle);
            }
        }

        kieSession.insert(solution);

        return new ResponseEntity<>(solutionMapper.toDto(solution), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved solution with passed id."),
            @ApiResponse(code = 400, message = "Solution with passed id can not be found."),
    })
    @GetMapping("/{id}")
    public ResponseEntity<SolutionDTO> getSolution(@PathVariable @Min(1) Integer id) {

        Solution solution = solutionService.findOne(id);
        return new ResponseEntity<>(solutionMapper.toDto(solution), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved solutions page."),
    })
    @GetMapping("/by-page")
    public ResponseEntity<Page<SolutionDTO>> getAllSolutionsPage(Pageable pageable) {
        Page<Solution> page = solutionService.findAll(pageable);
        List<SolutionDTO> solutionDTOS = toSolutionDTOList(page.toList());
        Page<SolutionDTO> pageSolutionDTOS = new PageImpl<>(solutionDTOS, page.getPageable(), page.getTotalElements());

        return new ResponseEntity<>(pageSolutionDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all solutions."),
    })
    @GetMapping()
    public ResponseEntity<List<SolutionDTO>> getAllSolution() {
        List<Solution> solutions = solutionService.findAll();
        List<SolutionDTO> solutionDTOS = new ArrayList<>();
        for (Solution solution : solutions) {
            solutionDTOS.add(solutionMapper.toDto(solution));
        }
        return new ResponseEntity<>(solutionDTOS, HttpStatus.OK);
    }

    private List<SolutionDTO> toSolutionDTOList(List<Solution> solutions) {
        List<SolutionDTO> solutionDTOS = new ArrayList<>();
        for (Solution solution : solutions) {
            solutionDTOS.add(solutionMapper.toDto(solution));
        }
        return solutionDTOS;
    }
}
