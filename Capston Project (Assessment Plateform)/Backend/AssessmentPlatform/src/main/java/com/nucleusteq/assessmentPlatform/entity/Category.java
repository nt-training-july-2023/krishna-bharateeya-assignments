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
@SequenceGenerator(name = "catSeq", initialValue = 3010, allocationSize = 1)
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catSeq")
	private int categoryId;
	
	@Column(nullable = false)
	private String categoryName;
	
	private String description;
	
	
}
