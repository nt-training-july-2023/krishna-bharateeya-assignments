package com.nucleusteq.assessmentPlatform;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * This class represents the entry point of the Assessment Platform application.
 */
@SpringBootApplication
public class AssessmentPlatformApplication {

    /**
     * The main method to start the Assessment Platform application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(AssessmentPlatformApplication.class, args);
    }

    /**
     * Creates and returns an instance of ModelMapper, which is used for object
     * mapping.
     *
     * @return An instance of ModelMapper.
     */
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
