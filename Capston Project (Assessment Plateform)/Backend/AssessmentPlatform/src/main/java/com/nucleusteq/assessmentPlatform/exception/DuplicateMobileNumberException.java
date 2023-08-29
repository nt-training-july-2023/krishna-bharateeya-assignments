package com.nucleusteq.assessmentPlatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateMobileNumberException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public DuplicateMobileNumberException(String message) {
		super(message);
	}
}