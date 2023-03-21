package com.example.restapi.controllers;

import com.example.restapi.jwt.JwtTokenHelper;
import com.example.restapi.payloads.EmployeeDto;
import com.example.restapi.payloads.JwtAuthRequest;
import com.example.restapi.payloads.JwtAuthResponse;
import com.example.restapi.services.EmployeeService;
import com.example.restapi.services.impl.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public String home(){
        return "this is home";
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest jwtAuthRequest)   {

        this.authenticate(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        return ResponseEntity.ok(response);
    }

    private void authenticate(String username, String password)  {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {

            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<EmployeeDto> register(@Valid @RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(this.employeeService.addEmployee(employeeDto), HttpStatus.CREATED);
    }
}
