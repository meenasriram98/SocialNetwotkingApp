package com.imaginea.training.service;

public class LogoutService {
	
	AuthenticationService authService=new AuthenticationService();
	ProcessService processService =new ProcessService();
	
	public void logout(String username) {
		if (authService.isUsernameAlreadyExists(username))
		{
			System.out.println("Logout is successful!");
			processService.postActionProcess(username);
		}
		else {
			System.out.println("Logout is failed!");
		}

	}
	

}
