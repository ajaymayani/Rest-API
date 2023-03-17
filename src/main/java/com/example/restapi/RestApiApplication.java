package com.example.restapi;

import com.example.restapi.entities.Role;
import com.example.restapi.helpers.AppConstant;
import com.example.restapi.repo.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class RestApiApplication implements CommandLineRunner {

    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        try{

            System.out.println(passwordEncoder.encode("abhay"));

            Role user = new Role();
            user.setId(AppConstant.ROLE_USER_ID);
            user.setName(AppConstant.ROLE_USER);

            Role admin = new Role();
            admin.setId(AppConstant.ROLE_ADMIN_ID);
            admin.setName(AppConstant.ROLE_ADMIN);
            List<Role> roleList = List.of(user, admin);
            List<Role> roles = this.roleRepo.saveAll(roleList);
            System.out.println(roles);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
