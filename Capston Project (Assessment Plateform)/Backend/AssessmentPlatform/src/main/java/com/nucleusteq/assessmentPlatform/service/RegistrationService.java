package com.nucleusteq.assessmentPlatform.service;


import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.nucleusteq.assessmentPlatform.entity.Registration;

@Service
public interface RegistrationService {

	

	public String addUser(Registration registration) ;

	public List<Registration> getAllRegistrations();

	public Map<String, String> loginUser(Registration inputRegistration) ;


}
