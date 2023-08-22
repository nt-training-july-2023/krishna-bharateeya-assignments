package com.nucleusteq.assessmentPlatform.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEmailException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public DuplicateEmailException(String message) {
		super(message);
	}
}
