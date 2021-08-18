package com.example.diary_0200;

import com.example.diary_0200.Controller.SignInController;
import com.example.diary_0200.Controller.TestController;
import com.example.diary_0200.Controller.SignUpCheckController;
import com.example.diary_0200.Controller.SignUpController;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletRegistrationConfig {

    @Bean
    public ServletRegistrationBean getSignUpControllerBean()
    {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new SignUpController());
        registrationBean.addUrlMappings("/SignUpController");

        return registrationBean;
    }

    @Bean
    public ServletRegistrationBean getSignInControllerBean()
    {

        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new SignInController());
        registrationBean.addUrlMappings("/SignInController");

        return registrationBean;
    }

    @Bean
    public ServletRegistrationBean getTestControllerBean()
    {

        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new TestController());
        registrationBean.addUrlMappings("/TestController");

        return registrationBean;
    }

    @Bean
    public ServletRegistrationBean getSignUpCheckBean()
    {

        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new SignUpCheckController());
        registrationBean.addUrlMappings("/SignUpCheckController");

        return registrationBean;
    }


}