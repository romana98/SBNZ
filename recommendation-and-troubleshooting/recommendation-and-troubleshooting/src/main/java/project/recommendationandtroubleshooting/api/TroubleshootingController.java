package project.recommendationandtroubleshooting.api;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
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
import project.recommendationandtroubleshooting.model.User;
import project.recommendationandtroubleshooting.model.troubleshooting.Bug;
import project.recommendationandtroubleshooting.model.troubleshooting.BugHistory;
import project.recommendationandtroubleshooting.model.troubleshooting.Bugs;
import project.recommendationandtroubleshooting.model.troubleshooting.Problem;
import project.recommendationandtroubleshooting.model.troubleshooting.cep.Warning;
import project.recommendationandtroubleshooting.service.impl.BugServiceImpl;
import project.recommendationandtroubleshooting.service.impl.UserServiceImpl;

import javax.validation.Valid;
import java.util.ArrayList;
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


    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned solution."),
            @ApiResponse(code = 400, message = "Failed to return solution."),
    })
    @PostMapping() // user keeps pressing no
    public ResponseEntity<Problem> solution(@Valid @RequestBody Problem problem) {
        kieSession.getAgenda().getAgendaGroup("troubleshooting").setFocus();
        kieSession.insert(problem);
        kieSession.fireAllRules();


        Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle : handlers) {
            Object obj = kieSession.getObject(handle);

            if (obj.getClass() == Problem.class)
                kieSession.delete(handle);
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
    public ResponseEntity<Bugs> bugFrequency() {
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

        return new ResponseEntity<>(bugs, HttpStatus.OK);
    }

    @GetMapping(value = "unsolved-bugs")
    public ResponseEntity<Bugs> unsolvedBugs() {
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

        return new ResponseEntity<>(bugs, HttpStatus.OK);
    }

    @GetMapping(value = "computer-state")
    public ResponseEntity<List<Warning>> getComputerState() {

        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kSession = kieContainer.newKieSession("eventsSession");

        //TODO: implement random CEP call

        kSession.fireAllRules();

        List<Warning> warnings = new ArrayList<>();
        Collection<Warning> newEvents = (Collection<Warning>) kSession.getObjects(new ClassObjectFilter(Bugs.class));
        for (int i = 0; i < newEvents.toArray().length; i++) {
            warnings.add((Warning) newEvents.toArray()[i]);
        }

        Collection<FactHandle> handlers = kSession.getFactHandles();
        for (FactHandle handle : handlers) {
            Object obj = kSession.getObject(handle);

            if (obj.getClass() == Warning.class)
                kSession.delete(handle);
        }

        return new ResponseEntity<>(warnings, HttpStatus.OK);
    }

}
