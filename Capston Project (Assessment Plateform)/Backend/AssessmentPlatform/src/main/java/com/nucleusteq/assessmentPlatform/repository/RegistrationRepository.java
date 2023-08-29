package com.nucleusteq.assessmentPlatform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nucleusteq.assessmentPlatform.entity.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

	Registration getByEmail(String email);
	Optional<Registration> findByEmail(String email);
	Optional<Registration> findByEmailAndPassword(String email, String encodePassword);
	Optional<Registration> findByMobileNumber(String mobileNumber);

}
