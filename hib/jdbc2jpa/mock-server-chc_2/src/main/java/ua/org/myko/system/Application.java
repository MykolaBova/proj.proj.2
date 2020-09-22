package ua.org.myko.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/*

@ComponentScan(basePackages = { "ua.org.myko.system.controllers",
    "ua.org.myko.system.controllers.example",
    "ua.org.myko.services.controllers"
    })

 */

@SpringBootApplication
//@EnableAutoConfiguration
@ComponentScan(basePackages = { "ua.org.myko.system.controllers",
    "ua.org.myko.system.controllers.example"
    })
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args)  {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
