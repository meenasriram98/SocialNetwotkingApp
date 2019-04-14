package com.imaginea.training.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.imaginea.training.data.UserRepository;
import com.imaginea.training.socialnetwork.domain.FriendRequest;
import com.imaginea.training.socialnetwork.domain.Person;

public class FriendService {
	
	public List<String> sendFriendRequest(FriendRequest friendRequest)
	{
		List<String> errors=new ArrayList<>();
		if(friendRequest.getpersonsEmail().equals(null)||friendRequest.getRecipientEmail().equals(null))
		{
			errors.add("entered email is null");
		}
		errors=UserRepository.getInstance().addRequest(friendRequest);
		return errors;
		
	}
	
	public List<FriendRequest> viewFriendRequests(String email)
	{
		List<FriendRequest> requests=new ArrayList<>();
		requests=UserRepository.getInstance().viewFriendRequests(email);
		return requests;
	}
	
	public boolean acceptRequest(String email,String senderEmail)
	{
		return UserRepository.getInstance().acceptFriendRequest(email,senderEmail);
		
	}

	public boolean declineRequest(String email, String senderEmail) {
		return UserRepository.getInstance().declineFriendRequest(email,senderEmail);
	}

	public List<Person> getFriends(String email)
	{
		return UserRepository.getInstance().returnListOfFriends(email);
	}

	public List<String> getMutualFriends(String email, String personsEmail) {
		
		List<String> mutualFriends=new ArrayList<>();
		Person person1 = UserRepository.getInstance().getPersonObject(email);
		Person person2= UserRepository.getInstance().getPersonObject(personsEmail);
		
		List<Person>friends1=person1.getFriends();
		List<Person>friends2=person1.getFriends();
		
		if(friends1==null||friends2==null||friends1.isEmpty()||friends2.isEmpty())
		{
			System.out.println("no mutual friends");
			return null;
		}
		
		for (Person person : friends1) {
			for (Person p : friends2) {
				if(person.getUniqueIdentifier().equals(p.getUniqueIdentifier()))
				{
					mutualFriends.add(person.getUniqueIdentifier());
				}
			}
			
		}
		return mutualFriends;
	}

	
}
