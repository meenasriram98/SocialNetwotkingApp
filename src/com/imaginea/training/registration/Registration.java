package com.imaginea.training.registration;

import com.imaginea.training.socialnetwork.domain.PersonalInfo;

public class Registration {
	
	private PersonalInfo personalInfo;
	private String password;
	
	public Registration(PersonalInfo personalInfo,final String password)
	{
		this.personalInfo=personalInfo;
		this.password=password;
	}
	
	public PersonalInfo getPersonalInfo()
	{
		return personalInfo;
	}

}
