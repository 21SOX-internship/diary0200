package com.example.diary_0200;

import com.example.diary_0200.Controller.SignUpController;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletRegistrationConfig {

    @Bean
    public ServletRegistrationBean getServletRegistrationBean()
    {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new SignUpController());
        registrationBean.addUrlMappings("/SignUpController");

        return registrationBean;
    }
}