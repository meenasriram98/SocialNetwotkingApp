package com.imaginea.training.service;

import java.util.HashMap;
import java.util.Map;

public class LoginService {
	AuthenticationService authenticationService=new AuthenticationService();
	UserInputService userInput=new UserInputService();
	MenuService menuService=new MenuService();
	
	public String processLogin()
	{
		Map<String,String> userDetails=new HashMap<>();
		userDetails=userInput.ProcessLoginInput();
		
		if (authenticationService.authenticate(userDetails.get("email"), userDetails.get("password"))) {
			postLogin();

		} else
		{
			System.out.println("Login is failed");
		}
		return null;
		
	}
	
	public void postLogin()
	{
		menuService.printHomeMenu();
	}

}
