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
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/Upload").setViewName("Upload");
        registry.addViewController("/Success").setViewName("Success");
        registry.addViewController("/Profile").setViewName("Profile");
        registry.addViewController("/download").setViewName("download");
        registry.addViewController("/change_file").setViewName("change_file");
        registry.addViewController("/style").setViewName("style");
//        registry.addViewController("css/style").setViewName("style");
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        super.addResourceHandlers(registry);
//////        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
////        registry.addResourceHandler("*.css").addResourceLocations("../../resources/static/css/");
//        registry.addResourceHandler("*.css").addResourceLocations("FilesProject/web/src/main/resources/static/css");
//    }

}