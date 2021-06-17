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
import project.recommendationandtroubleshooting.dto.BugDTO;
import project.recommendationandtroubleshooting.mapper.BugMapper;
import project.recommendationandtroubleshooting.model.troubleshooting.Bug;
import project.recommendationandtroubleshooting.service.impl.BugServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/bugs", produces = MediaType.APPLICATION_JSON_VALUE)
public class BugController {

    @Autowired
    BugServiceImpl bugService;

    @Autowired
    KieSession kieSession;

    private final BugMapper bugMapper;

    public BugController() {
        bugMapper = new BugMapper();
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created bug."),
            @ApiResponse(code = 400, message = "Bug already exists."),
    })
    @PostMapping()
    public ResponseEntity<BugDTO> createBug(@Valid @RequestBody BugDTO bugDTO) {

        Bug bug = bugMapper.toEntity(bugDTO);

        Bug savedBug = bugService.saveOne(bug);
        kieSession.insert(savedBug);

        return new ResponseEntity<>(bugMapper.toDto(savedBug), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted bug with passed id."),
            @ApiResponse(code = 400, message = "Bug with passed id can not be found."),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBug(@PathVariable @Min(1) Integer id) {

        bugService.delete(id);

        Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle : handlers) {
            Object obj = kieSession.getObject(handle);

            if (obj.getClass() == Bug.class) {
                if (((Bug) obj).getId().equals(id))
                    kieSession.delete(handle);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated bug with passed id."),
            @ApiResponse(code = 400, message = "Bug with passed id can not be found."),
    })
    @PutMapping()
    public ResponseEntity<BugDTO> updateBug(@Valid @RequestBody BugDTO bugDTO) {

        Bug bug = bugService.update(bugMapper.toEntity(bugDTO));

        Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle : handlers) {
            Object obj = kieSession.getObject(handle);

            if (obj.getClass() == Bug.class) {
                if (((Bug) obj).getId().equals(bug.getId()))
                    kieSession.delete(handle);
            }
        }

        kieSession.insert(bug);

        return new ResponseEntity<>(bugMapper.toDto(bug), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved bug with passed id."),
            @ApiResponse(code = 400, message = "Bug with passed id can not be found."),
    })
    @GetMapping("/{id}")
    public ResponseEntity<BugDTO> getBug(@PathVariable @Min(1) Integer id) {

        Bug bug = bugService.findOne(id);
        return new ResponseEntity<>(bugMapper.toDto(bug), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved bugs page."),
    })
    @GetMapping("/by-page")
    public ResponseEntity<Page<BugDTO>> getAllBugsPage(Pageable pageable) {
        Page<Bug> page = bugService.findAll(pageable);
        List<BugDTO> bugDTOS = toBugDTOList(page.toList());
        Page<BugDTO> pageBugDTOS = new PageImpl<>(bugDTOS, page.getPageable(), page.getTotalElements());

        return new ResponseEntity<>(pageBugDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all bugs."),
    })
    @GetMapping()
    public ResponseEntity<List<BugDTO>> getAllBugs() {
        List<Bug> bugs = bugService.findAll();
        List<BugDTO> bugDTOS = new ArrayList<>();
        for (Bug bug : bugs) {
            bugDTOS.add(bugMapper.toDto(bug));
        }
        return new ResponseEntity<>(bugDTOS, HttpStatus.OK);
    }

    private List<BugDTO> toBugDTOList(List<Bug> bugs) {
        List<BugDTO> bugDTOs = new ArrayList<>();
        for (Bug bug : bugs) {
            bugDTOs.add(bugMapper.toDto(bug));
        }
        return bugDTOs;
    }
}
