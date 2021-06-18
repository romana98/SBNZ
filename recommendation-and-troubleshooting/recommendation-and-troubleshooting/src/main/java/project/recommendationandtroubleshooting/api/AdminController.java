package project.recommendationandtroubleshooting.api;


import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import project.recommendationandtroubleshooting.mapper.AdminMapper;
import project.recommendationandtroubleshooting.model.Admin;
import project.recommendationandtroubleshooting.model.Authority;
import project.recommendationandtroubleshooting.service.impl.AdminServiceImpl;
import project.recommendationandtroubleshooting.service.impl.AuthorityService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/administrators", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationController authController;

    private final AdminMapper adminMapper;

    public AdminController() {
        adminMapper = new AdminMapper();
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created admin."),
            @ApiResponse(code = 400, message = "Admin with passed email already exists."),
    })
    @PostMapping()
    public ResponseEntity<UserDTO> createAdministrator(@Valid @RequestBody UserDTO adminDTO) {

        adminDTO.setPassword(passwordEncoder.encode(adminDTO.getPassword()));

        Admin verify = adminMapper.toEntityWithPass(adminDTO);

        int role = 1;
        List<Authority> auth = authorityService.findById(role);
        verify.setAuthorities(auth);
        verify.setVerified(true);

        Admin savedAdmin = adminService.saveOne(verify);
        return new ResponseEntity<>(adminMapper.toDto(savedAdmin), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted admin with passed id."),
            @ApiResponse(code = 400, message = "Admin with passed id can not be found."),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdministrator(@PathVariable @Min(1) Integer id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin adminLogged = (Admin) authentication.getPrincipal();

        if (adminLogged.getId().equals(id)) {
            return new ResponseEntity<>("You are logged in.", HttpStatus.BAD_REQUEST);
        }
        adminService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated admin with passed id."),
            @ApiResponse(code = 400, message = "Admin with passed id can not be found."),
    })
    @PutMapping()
    public ResponseEntity<?> updateAdministrator(@Valid @RequestBody UserDTO adminDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin adminLogged = (Admin) authentication.getPrincipal();

        if (!adminDTO.getEmail().equals(adminLogged.getEmail()) || !adminDTO.getId().equals(adminLogged.getId())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String password = adminDTO.getPassword();
        adminDTO.setPassword(adminDTO.getPassword().equals("________") ? adminLogged.getPassword() : passwordEncoder.encode(adminDTO.getPassword()));
        Admin admin = adminService.update(adminMapper.toEntityWithPass(adminDTO));

        if (!password.equals("________")) {
            authController.updatedLoggedIn(admin.getUsername(), password);
        }
        return new ResponseEntity<>(adminMapper.toDto(admin), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved admin with passed id."),
            @ApiResponse(code = 400, message = "Admin with passed id can not be found."),
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getAdministrator(@PathVariable Integer id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin adminLogged = (Admin) authentication.getPrincipal();

        Admin admin = adminService.findOne(id);

        if (admin.getEmail().equals(adminLogged.getEmail())) {
            UserDTO adminDTO = adminMapper.toDto(admin);
            return new ResponseEntity<>(adminDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved admins page."),
    })
    @GetMapping("/by-page")
    public ResponseEntity<Page<UserDTO>> getAllAdministratorsPage(Pageable pageable) {
        Page<Admin> page = adminService.findAll(pageable);
        List<UserDTO> adminDTOS = toAdminDTOList(page.toList());
        Page<UserDTO> pageAdminDTOS = new PageImpl<>(adminDTOS, page.getPageable(), page.getTotalElements());

        return new ResponseEntity<>(pageAdminDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all admins."),
    })
    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllAdministrators() {
        List<Admin> admins = adminService.findAll();
        List<UserDTO> adminsDTOS = new ArrayList<>();
        for (Admin admin : admins) {
            adminsDTOS.add(adminMapper.toDto(admin));
        }
        return new ResponseEntity<>(adminsDTOS, HttpStatus.OK);
    }

    private List<UserDTO> toAdminDTOList(List<Admin> admins) {
        List<UserDTO> adminDTOs = new ArrayList<>();
        for (Admin admin : admins) {
            adminDTOs.add(adminMapper.toDto(admin));
        }
        return adminDTOs;
    }
}
