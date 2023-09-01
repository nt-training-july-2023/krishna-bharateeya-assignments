package com.nucleusteq.assessmentPlatform.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration class for security-related beans and settings.
 */
@Configuration
public class SecurityConfiguration {

    /**
     * Creates and returns an instance of BCryptPasswordEncoder, which is used
     * for password encoding.
     *
     * @return An instance of BCryptPasswordEncoder for password encoding.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
