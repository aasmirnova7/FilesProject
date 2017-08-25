package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//Связь url со страницами html
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello_all").setViewName("hello_all");
        registry.addViewController("/hello_admin").setViewName("hello_admin");
        registry.addViewController("/forbidden").setViewName("forbidden");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/repeat_registration_error").setViewName("repeat_registration_error");
        registry.addViewController("/Upload").setViewName("Upload");
        registry.addViewController("/Success").setViewName("Success");

    }

}