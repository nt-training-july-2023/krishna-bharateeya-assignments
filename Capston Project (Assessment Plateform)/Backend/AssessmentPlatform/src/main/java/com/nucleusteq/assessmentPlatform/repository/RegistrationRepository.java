package com.nucleusteq.assessmentPlatform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nucleusteq.assessmentPlatform.entity.Registration;

/**
 * Repository interface for managing Registration entities.
 */
@Repository
public interface RegistrationRepository
        extends JpaRepository<Registration, Integer> {

    /**
     * Retrieves a Registration entity by email.
     *
     * @param email The email to search for.
     * @return The found Registration entity, if any.
     */
    Registration getByEmail(String email);

    /**
     * Finds a Registration entity by email.
     *
     * @param email The email to search for.
     * @return An Optional containing the found Registration entity, if any.
     */
    Optional<Registration> findByEmail(String email);

    /**
     * Finds a Registration entity by email and password.
     *
     * @param email          The email to search for.
     * @param encodePassword The encoded password to search for.
     * @return An Optional containing the found Registration entity, if any.
     */
    Optional<Registration> findByEmailAndPassword(String email,
            String encodePassword);

    /**
     * Finds a Registration entity by mobile number.
     *
     * @param mobileNumber The mobile number to search for.
     * @return An Optional containing the found Registration entity, if any.
     */
    Optional<Registration> findByMobileNumber(String mobileNumber);

}
