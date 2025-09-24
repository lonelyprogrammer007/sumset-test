package co.gov.sdp.spdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackageClasses = {AppcoreApplication.class})
@ComponentScan(basePackages = {"co.gov.sdp.spdd.data","co.gov.sdp"})
public class AppcoreApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AppcoreApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(getClass());
    }

}
