package example.foodhub.auth.controller;

import java.util.List;
import java.util.stream.Collectors;

import example.foodhub.customer.model.domain.Customer;
import example.foodhub.customer.repository.CustomerRepository;
import example.foodhub.employee.model.domain.Employee;
import example.foodhub.employee.repository.EmployeeRepository;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.foodhub.auth.config.JwtUtils;
import example.foodhub.auth.model.domain.SecurityUser;
import example.foodhub.auth.model.domain.User;
import example.foodhub.auth.model.request.LoginRequest;
import example.foodhub.auth.model.request.SignupRequest;
import example.foodhub.auth.model.response.JwtResponse;
import example.foodhub.auth.model.response.MessageResponse;
import example.foodhub.auth.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        logger.info(jwt);

        List<String> roles = securityUser.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                securityUser.getId(),
                securityUser.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        String userRole = signUpRequest.getRole();

        // Create new user's account
        User user = new User(
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), userRole);
        User savedUser = userRepository.save(user);

        switch (userRole) {
            case "ROLE_ADMIN":
//                Todo + super admin
                break;
            case "ROLE_CUSTOMER":
                Customer customer = new Customer(savedUser);
                customerRepository.save(customer);
                break;
            case "ROLE_EMPLOYEE":
                Employee employee = new Employee(savedUser);
                employeeRepository.save(employee);
                break;
            default:
                break;
        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
