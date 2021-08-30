package com.example.diary_0200;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

@SpringBootApplication
public class Diary0200Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Diary0200Application.class, args);
    }

}

