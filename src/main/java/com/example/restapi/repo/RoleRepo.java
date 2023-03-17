package com.example.restapi.repo;

import com.example.restapi.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role ,Integer> {
}
