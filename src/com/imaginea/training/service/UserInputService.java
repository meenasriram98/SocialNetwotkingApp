package com.imaginea.training.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.imaginea.training.socialnetwork.domain.Gender;

public class UserInputService {
	Scanner sc=new Scanner(System.in);
	
	public Map<String, String> ProcessLoginInput()
	{
		Map<String,String> userDetails=new HashMap<>();
		System.out.println("Login process is initiated. Please provide below details");
		System.out.println("Email:");
		String email = sc.next();
		System.out.println("Password:");
		String password = sc.next();
		userDetails.put("email",email);
		userDetails.put("password",password);
		return userDetails;
	}
	
	public Map<String, String> ProcessRegistrationInput()
	{
		Map<String,String> userDetails=new HashMap<>();
		System.out.println("Registration process is initiated. Please provide below details");
		System.out.println("Phone:");
		String phone = sc.next();
		userDetails.put("phone",phone);
		
		System.out.println("E-Mail:");
		String email = sc.next();
		userDetails.put("email",email);
		
		System.out.println("firstName:");
		String firstName = sc.next();
		userDetails.put("firstName",firstName);
		
		System.out.println("lastName:");
		String lastName = sc.next();
		userDetails.put("lastName",lastName);
		
		System.out.println("Gender M/F:");
		String userGenderInput = sc.next();
		userDetails.put("gender",userGenderInput);

		System.out.println("Username:");
		String userName = sc.next();
		userDetails.put("userName",userName);

		System.out.println("password:");
		String password = sc.next();
		userDetails.put("password",password);
		
		return userDetails;
	}


}
