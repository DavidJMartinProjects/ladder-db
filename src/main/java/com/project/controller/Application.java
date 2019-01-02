package com.project.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.project.business", "com.project.domain","com.project.topten", "com.project.active.league", "com.project.controller", "com.project.custom.league" })
@EntityScan( basePackages = {"com.project.topten", "com.project.domain.datatable", "com.project.controller"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
