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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import project.recommendationandtroubleshooting.dto.UserDTO;
import project.recommendationandtroubleshooting.mapper.UserMapper;
import project.recommendationandtroubleshooting.model.User;
import project.recommendationandtroubleshooting.model.troubleshooting.Bug;
import project.recommendationandtroubleshooting.service.impl.UserServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationController authController;

    @Autowired
    KieSession kieSession;


    private final UserMapper userMapper;

    public UserController() {
        userMapper = new UserMapper();
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted user with passed id."),
            @ApiResponse(code = 400, message = "User with passed id can not be found."),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable @Min(1) Integer id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userLogged = (User) authentication.getPrincipal();

        if (userLogged.getId().equals(id)) {
            return new ResponseEntity<>("You are logged in.", HttpStatus.BAD_REQUEST);
        }
        userService.delete(id);

        Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle : handlers) {
            Object obj = kieSession.getObject(handle);

            if (obj.getClass() == User.class) {
                if (((User) obj).getId().equals(id))
                    kieSession.delete(handle);
            }
        }


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated user with passed id."),
            @ApiResponse(code = 400, message = "User with passed id can not be found."),
    })
    @PutMapping()
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO userDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userLogged = (User) authentication.getPrincipal();

        if (!userDTO.getEmail().equals(userLogged.getEmail()) || !userDTO.getId().equals(userLogged.getId())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String password = userDTO.getPassword();
        userDTO.setPassword(userDTO.getPassword().equals("________") ? userLogged.getPassword() : passwordEncoder.encode(userDTO.getPassword()));
        User user = userService.update(userMapper.toEntity(userDTO));

        if (!password.equals("________")) {
            authController.updatedLoggedIn(user.getUsername(), password);
        }

        Collection<FactHandle> handlers = kieSession.getFactHandles();
        for (FactHandle handle : handlers) {
            Object obj = kieSession.getObject(handle);

            if (obj.getClass() == User.class) {
                if (((User) obj).getId().equals(user.getId()))
                    kieSession.delete(handle);
            }
        }

        kieSession.insert(user);


        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user with passed id."),
            @ApiResponse(code = 400, message = "User with passed id can not be found."),
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userLogged = (User) authentication.getPrincipal();

        User user = userService.findOne(id);

        if (user.getEmail().equals(userLogged.getEmail())) {
            UserDTO adminDTO = userMapper.toDto(user);
            return new ResponseEntity<>(adminDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved users page."),
    })
    @GetMapping("/by-page")
    public ResponseEntity<Page<UserDTO>> getAllUsersPage(Pageable pageable) {
        Page<User> page = userService.findAll(pageable);
        List<UserDTO> userDTOS = toUserDTOList(page.toList());
        Page<UserDTO> pageUserDTOS = new PageImpl<>(userDTOS, page.getPageable(), page.getTotalElements());

        return new ResponseEntity<>(pageUserDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all users."),
    })
    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserDTO> usersDTOS = new ArrayList<>();
        for (User user : users) {
            usersDTOS.add(userMapper.toDto(user));
        }
        return new ResponseEntity<>(usersDTOS, HttpStatus.OK);
    }

    private List<UserDTO> toUserDTOList(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(userMapper.toDto(user));
        }
        return userDTOs;
    }
}
