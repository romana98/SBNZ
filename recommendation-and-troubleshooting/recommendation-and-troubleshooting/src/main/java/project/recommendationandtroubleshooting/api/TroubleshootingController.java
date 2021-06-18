package project.recommendationandtroubleshooting.api;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import project.recommendationandtroubleshooting.dto.BugsDTO;
import project.recommendationandtroubleshooting.mapper.BugMapper;
import project.recommendationandtroubleshooting.model.User;
import project.recommendationandtroubleshooting.model.troubleshooting.*;
import project.recommendationandtroubleshooting.model.troubleshooting.cep.Warning;
import project.recommendationandtroubleshooting.service.impl.BugServiceImpl;
import project.recommendationandtroubleshooting.service.impl.UserServiceImpl;
import project.recommendationandtroubleshooting.util.ActivateCEP;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/troubleshooting", produces = MediaType.APPLICATION_JSON_VALUE)
public class TroubleshootingController {

    @Autowired
    KieSession kieSession;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    BugServiceImpl bugService;

    private final BugMapper bugMapper;

    public TroubleshootingController() {
        bugMapper = new BugMapper();
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned solution."),
            @ApiResponse(code = 400, message = "Failed to return solution."),
    })
    @PostMapping() // user keeps pressing no
    public ResponseEntity<Problem> solution(@Valid @RequestBody Problem problem) {
        Solution currentSolution = problem.getCurrentSolution();

        kieSession.getAgenda().getAgendaGroup("troubleshooting").setFocus();
        kieSession.insert(problem);
        kieSession.fireAllRules();


        Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle : handlers) {
            Object obj = kieSession.getObject(handle);

            if (obj.getClass() == Problem.class)
                kieSession.delete(handle);
        }

        if (problem.getCurrentSolution() == null) {
            problem.setCurrentSolution(currentSolution);
        }
        return new ResponseEntity<>(problem, HttpStatus.OK);
    }


    @PutMapping() // user presses yes
    public ResponseEntity<User> updateBugHistory(@Valid @RequestBody Problem problem) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedIn = (User) authentication.getPrincipal();

        Bug bug = bugService.findByDescription(problem.getProblems());

        BugHistory bugHistory = new BugHistory(new Date(), problem.getCurrentSolution(), bug);

        User user = userService.addToBugHistory(loggedIn.getId(), bugHistory);

        Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle : handlers) {
            Object obj = kieSession.getObject(handle);

            if (obj.getClass() == User.class) {
                if (((User) obj).getId().equals(user.getId()))
                    kieSession.delete(handle);
            }
        }
        kieSession.insert(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned solution."),
            @ApiResponse(code = 400, message = "Failed to return solution."),
    })
    @GetMapping(value = "bug-frequency")
    public ResponseEntity<BugsDTO> bugFrequency() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedIn = (User) authentication.getPrincipal();


        kieSession.getAgenda().getAgendaGroup("bug_frequency").setFocus();

        Bugs bugs = new Bugs();
        kieSession.insert(bugs);
        kieSession.setGlobal("userId", loggedIn.getId());

        kieSession.fireAllRules();

        // Collection<Bugs> newEvents = (Collection<Bugs>) kieSession.getObjects(new ClassObjectFilter(Bugs.class));
        // Bugs userBugs = (Bugs) newEvents.toArray()[0];

        Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle : handlers) {
            Object obj = kieSession.getObject(handle);

            if (obj.getClass() == Bugs.class)
                kieSession.delete(handle);
        }
        kieSession.setGlobal("userId", -1);

        BugsDTO bugsDTO = new BugsDTO();
        for (Bug bug : bugs.getBugs()) {
            bugsDTO.addBug(bugMapper.toDto(bug));
        }

        return new ResponseEntity<>(bugsDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "unsolved-bugs")
    public ResponseEntity<BugsDTO> unsolvedBugs() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedIn = (User) authentication.getPrincipal();


        kieSession.getAgenda().getAgendaGroup("unsolved_bugs").setFocus();

        Bugs bugs = new Bugs();

        kieSession.insert(bugs);
        kieSession.setGlobal("userId", loggedIn.getId());

        kieSession.fireAllRules();

        // Collection<Bugs> newEvents = (Collection<Bugs>) kieSession.getObjects(new ClassObjectFilter(Bugs.class));
        // Bugs userBugs = (Bugs) newEvents.toArray()[0];

        Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle : handlers) {
            Object obj = kieSession.getObject(handle);

            if (obj.getClass() == Bugs.class)
                kieSession.delete(handle);
        }
        kieSession.setGlobal("userId", -1);

        BugsDTO bugsDTO = new BugsDTO();
        for (Bug bug : bugs.getBugs()) {
            bugsDTO.addBug(bugMapper.toDto(bug));
        }
        return new ResponseEntity<>(bugsDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "computer-state")
    public ResponseEntity<List<Warning>> getComputerState() {

        List<Warning> warnings = ActivateCEP.activate();

        return new ResponseEntity<>(warnings, HttpStatus.OK);
    }

}
