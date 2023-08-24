package com.nucleusteq.assessmentPlatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity 
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "userSeq", initialValue = 1010, allocationSize = 1)
public class Registration {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
	int userId;
	
	@Column(nullable = false)
	String firstName;
	
	@Column(nullable = false)
	String lastName;
	
	@Column(nullable = false, unique = true)
	String mobileNumber;
	
	
	@Column(nullable = false)
	String userRole;
	
	@Column(nullable = false, unique = true)
	String email;
	
	@Column(nullable = false)
	String password;
	
}
