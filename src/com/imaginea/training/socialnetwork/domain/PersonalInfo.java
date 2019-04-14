package com.imaginea.training.socialnetwork.domain;

import java.util.Date;

public class PersonalInfo {
	
	private String phone;
	private String email;
	private String username;
	private String firstName;
	private String lastName;
	private Gender gender;
	private Date   dateOfBirth;
	
	public PersonalInfo(String phone,String email,String username,String firstName,String lastName,Gender gender)
	{
		this.phone=phone;
		this.email = email;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		//this.dateOfBirth = dateOfBirth;

	}
	
	public String getNumber()
	{
		return this.phone;
	}
	
	public String name()
	{
		return firstName+""+lastName;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public boolean isEmailEqualTo(String email)
	{
		if(email==null)
		{
			return false;
		}
		else
			return this.email.equalsIgnoreCase(email);
	}
}
