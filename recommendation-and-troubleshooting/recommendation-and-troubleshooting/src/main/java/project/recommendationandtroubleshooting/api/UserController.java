package project.recommendationandtroubleshooting.api;


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
import project.recommendationandtroubleshooting.service.impl.UserServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
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

    private final UserMapper userMapper;

    public UserController() {
        userMapper = new UserMapper();
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable @Min(1) Integer id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userLogged = (User) authentication.getPrincipal();

        if (userLogged.getId().equals(id)) {
            return new ResponseEntity<>("You are logged in.", HttpStatus.BAD_REQUEST);
        }
        if (userService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>("USer not found.", HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO userDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userLogged = (User) authentication.getPrincipal();

        if (!userDTO.getEmail().equals(userLogged.getEmail()) || !userDTO.getId().equals(userLogged.getId())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String password = userDTO.getPassword();
        userDTO.setPassword(userDTO.getPassword().equals("________") ? userLogged.getPassword() : passwordEncoder.encode(userDTO.getPassword()));
        User user = userService.update(userMapper.toEntity(userDTO));

        if (user == null) {
            return new ResponseEntity<>("Email already exists.", HttpStatus.BAD_REQUEST);
        }

        if (!password.equals("________")) {
            authController.updatedLoggedIn(user.getUsername(), password);
        }
        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userLogged = (User) authentication.getPrincipal();

        User user = userService.findOne(id);

        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        } else if (user.getEmail().equals(userLogged.getEmail())) {
            UserDTO adminDTO = userMapper.toDto(user);
            return new ResponseEntity<>(adminDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @RequestMapping(value = "/by-page", method = RequestMethod.GET)
    public ResponseEntity<Page<UserDTO>> getAllUsers(Pageable pageable) {
        Page<User> page = userService.findAll(pageable);
        List<UserDTO> culturalContentCategoryDTOS = toUserDTOList(page.toList());
        Page<UserDTO> pageCulturalContentCategoryDTOS = new PageImpl<>(culturalContentCategoryDTOS, page.getPageable(), page.getTotalElements());

        return new ResponseEntity<>(pageCulturalContentCategoryDTOS, HttpStatus.OK);
    }

    private List<UserDTO> toUserDTOList(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(userMapper.toDto(user));
        }
        return userDTOs;
    }
}
