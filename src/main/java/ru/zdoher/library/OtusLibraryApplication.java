package ru.zdoher.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class OtusLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(OtusLibraryApplication.class, args);
    }

}
