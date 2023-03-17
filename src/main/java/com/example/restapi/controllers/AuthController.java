package com.example.restapi.controllers;

import com.example.restapi.payloads.EmployeeDto;
import com.example.restapi.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public String home(){
        return "this is home";
    }

    @GetMapping("/login")
    public String    loging() {
        return "login";
    }

    @PostMapping("/register")
    public ResponseEntity<EmployeeDto> register(@Valid @RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(this.employeeService.addEmployee(employeeDto), HttpStatus.CREATED);
    }
}
