package com.imaginea.training.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.imaginea.training.registration.Registration;
import com.imaginea.training.socialnetwork.domain.Gender;
import com.imaginea.training.socialnetwork.domain.PersonalInfo;

public class RegistrationService {
	
	UserInputService input=new UserInputService();
	AuthenticationService authService=new AuthenticationService();
	DataStoreService dataService=new DataStoreService();
	MenuService menuService=new MenuService();
	ProcessService process=new ProcessService();
	
	public void processRegistration()
	{
		Map<String,String> userDetails=new HashMap<>();

		Gender gender = Gender.MALE;
		if (userDetails.get("gender").equals("F"))
			gender = Gender.FEMALE;
		
		PersonalInfo personalInfo=new PersonalInfo(userDetails.get("phone"), userDetails.get("email"), userDetails.get("userName"), userDetails.get("firstName"), userDetails.get("lastName"), gender);
		Registration registration=new Registration(personalInfo,userDetails.get("password"));
		register(registration,userDetails.get("userName"),userDetails.get("password"));
		
	}
	
	private void register(Registration registration,String email,String password)
	{
		List<String> errors=authService.validate(registration);
		
		if (!errors.isEmpty()) {
			errors.forEach(System.out::println);
			return;
		}
		dataService.saveToRepository(registration);
		dataService.storeUserCredentials(email, password);
		System.out.println("Account created successfully!!");
		postRegistration();
//		printMenu();
//		processInput();


	}
	
	public void postRegistration()
	{
		menuService.printMenu();
		process.processInput();
		
	}

}
