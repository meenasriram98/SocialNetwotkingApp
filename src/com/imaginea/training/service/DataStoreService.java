package com.imaginea.training.service;

import com.imaginea.training.data.AuthenticationData;
import com.imaginea.training.data.UserRepository;
import com.imaginea.training.registration.Registration;
import com.imaginea.training.socialnetwork.domain.Person;

public class DataStoreService {
	 UserRepository usersRepository=UserRepository.getInstance();
	 AuthenticationData authData=AuthenticationData.getInstance();
	
	public UserRepository saveToRepository(Registration register)
	{
		usersRepository.addUser(new Person(register));
		return usersRepository;
		
	}
	
	public void storeUserCredentials(String email, String password) {
		authData.store(email, password);
	}

}
