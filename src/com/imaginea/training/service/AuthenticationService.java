package com.imaginea.training.service;

import com.imaginea.training.data.AuthenticationData;

public class AuthenticationService {
	
	public boolean authenticate(final String userName,final String password)
	{
		AuthenticationData authneicationData=AuthenticationData.getInstance();
		return authneicationData.isUserNamePasswordMatching(userName, password);
	}
	
	public boolean logout(String userName)
	{
		return true;
	}
}
