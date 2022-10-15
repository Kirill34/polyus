package com.example.polyus;

import controller.UserController;
import model.VehicleOrder;
import model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repo.UserRepository;

@EntityScan(basePackageClasses = {User.class, VehicleOrder.class})
@SpringBootApplication(scanBasePackageClasses = {UserRepository.class})
@ComponentScan(basePackageClasses = {UserController.class})
@EnableJpaRepositories(basePackageClasses = {UserRepository.class})
@Configuration
public class PolyusApplication {

    public static void main(String[] args) {
        SpringApplication.run(PolyusApplication.class, args);
    }

}
