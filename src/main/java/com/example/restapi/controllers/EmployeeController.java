package com.example.restapi.controllers;

import com.example.restapi.entities.Role;
import com.example.restapi.helpers.AppConstant;
import com.example.restapi.payloads.ApiResponse;
import com.example.restapi.payloads.EmployeeDto;
import com.example.restapi.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    //ADD EMPLOYEE
    @PostMapping
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDto employeeDto ){
        EmployeeDto employeeDto1 = this.employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(employeeDto1, HttpStatus.CREATED);
    }


    //UPDATE EMPLOYEE
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto,@PathVariable Integer employeeId)
    {
        EmployeeDto updateEmployee = this.employeeService.updateEmployee(employeeId, employeeDto);
        return ResponseEntity.ok(updateEmployee);
    }

    //DELETE EMPLOYEE
    @DeleteMapping("{employeeId}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Integer employeeId)
    {
        this.employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(new ApiResponse(true,"Employee deleted successfully...!"),HttpStatus.OK);
    }

    //GET EMPLOYEE BY ID
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer employeeId)
    {
        EmployeeDto employeeDto = this.employeeService.fetchEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    //GET ALL EMPLOYEE
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployeeById()
    {
        List<EmployeeDto> employeeDtos = this.employeeService.fetchAllEmployee();
        return ResponseEntity.ok(employeeDtos);
    }
}
