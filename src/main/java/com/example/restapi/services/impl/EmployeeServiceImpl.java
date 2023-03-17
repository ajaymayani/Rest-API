package com.example.restapi.services.impl;

import com.example.restapi.entities.Employee;
import com.example.restapi.entities.Role;
import com.example.restapi.exceptions.ResourceNotFoundException;
import com.example.restapi.helpers.AppConstant;
import com.example.restapi.payloads.EmployeeDto;
import com.example.restapi.repo.EmployeeRepo;
import com.example.restapi.repo.RoleRepo;
import com.example.restapi.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee employee = this.modelMapper.map(employeeDto, Employee.class);
        employee.setPassword(this.passwordEncoder.encode(employeeDto.getPassword()));
        Role role_user = this.roleRepo.findById(AppConstant.ROLE_USER_ID).get();
        employee.getRoles().add(role_user);
        Employee savedEmployee = this.employeeRepo.save(employee);
        return this.modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployee(Integer employeeId, EmployeeDto employeeDto) {

        //get employee
        Employee employee = this.employeeRepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "employee id :", employeeId));

        //set new value in employee
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPassword(this.passwordEncoder.encode(employeeDto.getPassword()));
        employee.setDoj(employeeDto.getDoj());

        //update employee
        Employee updatedEmployee = this.employeeRepo.save(employee);

        return this.modelMapper.map(updatedEmployee, EmployeeDto.class);
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        //get employee
        Employee employee = this.employeeRepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "employee id :", employeeId));
        this.employeeRepo.delete(employee);
    }

    @Override
    public EmployeeDto fetchEmployeeById(Integer employeeId) {
        //get employee
        Employee employee = this.employeeRepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "employee id :", employeeId));
        return this.modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> fetchAllEmployee() {

        //fetch all employee from the database
        List<Employee> employees = this.employeeRepo.findAll();
        return employees.stream().map(employee -> this.modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
    }
}
