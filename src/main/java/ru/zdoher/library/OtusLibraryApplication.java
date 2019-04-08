package ru.zdoher.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@SpringBootApplication
public class OtusLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(OtusLibraryApplication.class, args);


    }
}
