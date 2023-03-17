package com.example.restapi.services.impl;

import com.example.restapi.entities.Employee;
import com.example.restapi.payloads.EmployeeDto;
import com.example.restapi.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = this.employeeRepo.findByEmail(username);
        if (employee == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetailsImpl(employee);
    }
}
