package ru.zdoher.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.zdoher.library.service.AuthorService;
import ru.zdoher.library.service.AuthorServiceImpl;

import javax.sql.DataSource;

@SpringBootApplication
public class OtusLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(OtusLibraryApplication.class, args);
    }

}
