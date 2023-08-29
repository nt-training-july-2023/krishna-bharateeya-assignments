package com.nucleusteq.assessmentPlatform.service;


import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.nucleusteq.assessmentPlatform.entity.Registration;
import com.nucleusteq.assessmentPlatform.exception.UserEmailDomainException;
import com.nucleusteq.assessmentPlatform.exception.UserNotFoundException;

@Service
public interface RegistrationService {

	

	public String addUser(Registration registration) throws UserEmailDomainException ;

	public List<Registration> getAllRegistrations();

	public Map<String, String> loginUser(Registration inputRegistration) throws UserNotFoundException ;


}
