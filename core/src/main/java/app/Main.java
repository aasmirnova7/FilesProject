package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"config", "auth","controllers","services"})
public class Main {

    public static void main(String[] args) throws Throwable{
        SpringApplication.run(Main.class,args);

    }
}