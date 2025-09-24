package co.gov.sdp.spdd.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EntityScan(basePackageClasses = {AppwebApplication.class})
@ComponentScan(basePackages = {"co.gov.sdp"})
public class AppwebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AppwebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class<AppwebApplication> applicationClass = AppwebApplication.class;

}
