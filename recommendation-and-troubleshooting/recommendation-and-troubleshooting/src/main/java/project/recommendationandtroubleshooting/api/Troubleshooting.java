package project.recommendationandtroubleshooting.api;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.kie.api.runtime.ClassObjectFilter;
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
import project.recommendationandtroubleshooting.model.troubleshooting.Bugs;
import project.recommendationandtroubleshooting.model.troubleshooting.Problem;
import project.recommendationandtroubleshooting.model.troubleshooting.cep.Warning;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/troubleshooting", produces = MediaType.APPLICATION_JSON_VALUE)
public class Troubleshooting {

    @Autowired
    KieSession kieSession;

    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned solution."),
            @ApiResponse(code = 400, message = "Failed to return solution."),
    })
    @PostMapping()
    public ResponseEntity<Problem> solution(@Valid @RequestBody Problem problem) {
        kieSession.getAgenda().getAgendaGroup("troubleshooting").setFocus();
        kieSession.insert(problem);
        kieSession.fireAllRules();

        Collection<Problem> newEvents = (Collection<Problem>) kieSession.getObjects(new ClassObjectFilter(Problem.class));
        problem = (Problem) newEvents.toArray()[0];

        Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle : handlers) {
            Object obj = kieSession.getObject(handle);

            if (obj.getClass() == Problem.class)
                kieSession.delete(handle);
        }

        return new ResponseEntity<>(problem, HttpStatus.CREATED);
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

        kieSession.insert(new Bugs());
        kieSession.setGlobal("userId", loggedIn.getId());

        kieSession.fireAllRules();

        Collection<Bugs> newEvents = (Collection<Bugs>) kieSession.getObjects(new ClassObjectFilter(Bugs.class));
        Bugs userBugs = (Bugs) newEvents.toArray()[0];

        Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle : handlers) {
            Object obj = kieSession.getObject(handle);

            if (obj.getClass() == Bugs.class)
                kieSession.delete(handle);
        }
        kieSession.setGlobal("userId", -1);

        return new ResponseEntity<>(userBugs, HttpStatus.OK);
    }

    @GetMapping(value = "unsolved-bugs")
    public ResponseEntity<Bugs> unsolvedBugs() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedIn = (User) authentication.getPrincipal();


        kieSession.getAgenda().getAgendaGroup("unsolved_bugs").setFocus();

        kieSession.insert(new Bugs());
        kieSession.setGlobal("userId", loggedIn.getId());

        kieSession.fireAllRules();

        Collection<Bugs> newEvents = (Collection<Bugs>) kieSession.getObjects(new ClassObjectFilter(Bugs.class));
        Bugs userBugs = (Bugs) newEvents.toArray()[0];

        Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle : handlers) {
            Object obj = kieSession.getObject(handle);

            if (obj.getClass() == Bugs.class)
                kieSession.delete(handle);
        }
        kieSession.setGlobal("userId", -1);

        return new ResponseEntity<>(userBugs, HttpStatus.OK);
    }

    @GetMapping(value = "cep")
    public ResponseEntity<List<Warning>> cep() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedIn = (User) authentication.getPrincipal();


        kieSession.getAgenda().getAgendaGroup("unsolved_bugs").setFocus();

        //TODO: implement random CEP call

        kieSession.fireAllRules();

        List<Warning> warnings = new ArrayList<>();
        Collection<Warning> newEvents = (Collection<Warning>) kieSession.getObjects(new ClassObjectFilter(Bugs.class));
        for (int i = 0; i < newEvents.toArray().length; i++) {
            warnings.add((Warning) newEvents.toArray()[i]);
        }

        Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle : handlers) {
            Object obj = kieSession.getObject(handle);

            if (obj.getClass() == Warning.class)
                kieSession.delete(handle);
        }

        return new ResponseEntity<>(warnings, HttpStatus.OK);
    }

}
