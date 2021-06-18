package project.recommendationandtroubleshooting.api;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import project.recommendationandtroubleshooting.dto.UserDTO;
import project.recommendationandtroubleshooting.dto.UserLoginDTO;
import project.recommendationandtroubleshooting.dto.UserTokenStateDTO;
import project.recommendationandtroubleshooting.mapper.UserMapper;
import project.recommendationandtroubleshooting.model.Admin;
import project.recommendationandtroubleshooting.model.Authority;
import project.recommendationandtroubleshooting.model.Person;
import project.recommendationandtroubleshooting.model.User;
import project.recommendationandtroubleshooting.security.TokenUtils;
import project.recommendationandtroubleshooting.service.impl.AdminServiceImpl;
import project.recommendationandtroubleshooting.service.impl.AuthorityService;
import project.recommendationandtroubleshooting.service.impl.CustomUserDetailsServiceImpl;
import project.recommendationandtroubleshooting.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

//123qweASD
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    KieSession kieSession;

    private UserMapper userMapper;

    public AuthenticationController() {
        userMapper = new UserMapper();
    }

    // Prvi endpoint koji pogadja korisnik kada se loguje.
    // Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
    @PostMapping("/log-in")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserLoginDTO authenticationRequest,
                                                       HttpServletResponse response) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));

        // Ubaci korisnika u trenutni security kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        Person person = (Person) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(person.getUsername(), person.getId(), person.getAuthorities().get(0).getAuthority()); // prijavljujemo se na sistem sa email adresom

        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(new UserTokenStateDTO(jwt));
    }

    // Endpoint za registraciju novog korisnika
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserDTO userRequest) throws Exception {

        User existUser = userService.findByEmail(userRequest.getEmail());
        Admin existAdmin = adminService.findByEmail(userRequest.getEmail());
        if (existUser != null || existAdmin != null) {
            return new ResponseEntity<>("Email already exists.", HttpStatus.BAD_REQUEST);
        }
        existUser = userMapper.toEntity(userRequest);
        existUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        int role = 2;
        List<Authority> auth = authorityService.findById(role);
        existUser.setAuthorities(auth);
        existUser.setVerified(false);

        User newUSer = userService.registerUser(existUser);

        if (newUSer == null) {
            return new ResponseEntity<>("Email already exists.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userMapper.toDto(newUSer), HttpStatus.CREATED);
    }

    // Endpoint za aktivaciju naloga
    @PostMapping("/activate/{id}")
    public ResponseEntity<?> activate(@PathVariable Integer id) {
        User regUser = userService.activateAccount(id);

        if (regUser == null)
            return new ResponseEntity<>("Activation failed.", HttpStatus.BAD_REQUEST);

        Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle : handlers) {
            Object obj = kieSession.getObject(handle);

            if (obj.getClass() == User.class) {
                if (((User) obj).getId().equals(regUser.getId()))
                    kieSession.delete(handle);
            }
        }

        kieSession.insert(regUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // U slucaju isteka vazenja JWT tokena, endpoint koji se poziva da se token osvezi
    @PostMapping(value = "/refresh")
    public ResponseEntity<UserTokenStateDTO> refreshAuthenticationToken(HttpServletRequest request) {

        String token = tokenUtils.getToken(request);
        String email = this.tokenUtils.getEmailFromToken(token);
        Person person = (Person) this.userDetailsService.loadUserByUsername(email);

        if (this.tokenUtils.canTokenBeRefreshed(token, person.getLastPasswordResetDate())) {
            String refreshedToken = tokenUtils.refreshToken(token);
            int expiresIn = tokenUtils.getExpiredIn();

            return ResponseEntity.ok(new UserTokenStateDTO(refreshedToken));
        } else {
            UserTokenStateDTO userTokenState = new UserTokenStateDTO();
            return ResponseEntity.badRequest().body(userTokenState);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') || hasRole('ROLE_USER')")
    public void updatedLoggedIn(String email, String password) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
