package com.imaginea.training.service;

import java.util.ArrayList;
import java.util.List;

import com.imaginea.training.data.AuthenticationData;
import com.imaginea.training.registration.Registration;

public class AuthenticationService {
	
	static AuthenticationData authneicationData=AuthenticationData.getInstance();
			
	public boolean authenticate(final String userName,final String password)
	{
		return authneicationData.isUserNamePasswordMatching(userName, password);
	}
	
	public List<String> validate(Registration registration)
	{
		List<String> errors = new ArrayList<>();
	if (isUsernameAlreadyExists(registration.getPersonalInfo().getUsername()))
		errors.add("Username already exists");

	if (isEmailAlreadyExists(registration.getPersonalInfo().getEmail()))
		errors.add("Email already exists");

	return errors;
	}
	
	public boolean isEmailAlreadyExists(String email) {
		return false;
	}

	public boolean isUsernameAlreadyExists(final String username) {
		return authneicationData.isUserNameAlreadyExists(username);
	}
	
}
