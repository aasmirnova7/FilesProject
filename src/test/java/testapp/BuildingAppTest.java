package testapp;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.context.TestConfiguration;

@SpringBootApplication(scanBasePackages = {"config", "spring"})
public class BuildingAppTest {

    @Test
    public void TestRunSpring(){
        SpringApplication.run(BuildingAppTest.class);
    }
}
