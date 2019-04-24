package com.imaginea.training.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.imaginea.training.data.UserRepository;
import com.imaginea.training.socialnetwork.domain.FriendRequest;
import com.imaginea.training.socialnetwork.domain.Person;

public class FriendService {
	
	Scanner sc=new Scanner(System.in);
	UserInputService inputService=new UserInputService();
	AuthenticationService authService=new AuthenticationService();
	ProcessService processService=new ProcessService();
	
	public void makeRequest(String email)
	{
		System.out.println("enter email of the person");
		String requestEmail=sc.next();
		if(!authService.isUsernameAlreadyExists(requestEmail))
		{
			System.out.println("enter correct email");
			processService.postActionProcess(requestEmail);;
		}
		FriendRequest friendRequest=new FriendRequest(email,requestEmail);
		processSendFriendRequest(friendRequest,email);
	}
	
	public void processSendFriendRequest(FriendRequest friendRequest,String email)
	{
		List<String> errors=new ArrayList<>();
		errors=sendFriendRequest(friendRequest);
		if(!errors.isEmpty())
		{
			errors.forEach(System.out::println);
		}
		else
		{
			System.out.println("Friend request sent successfully");
		}
		processService.postActionProcess(email);
		
	}
	
	public void printMutualFriends(String email, String personsEmail) {
		List<String> mutualFriends=new ArrayList<>();
		mutualFriends=getMutualFriends(email,personsEmail);
		if(mutualFriends!=null)
		{
			mutualFriends.forEach(System.out::println);
		}	
	}
	
	public List<String> sendFriendRequest(FriendRequest friendRequest)
	{
		List<String> errors=new ArrayList<>();
		if(friendRequest.getpersonsEmail().equals(null)||friendRequest.getRecipientEmail().equals(null))
		{
			errors.add("enter correct email");
			return errors;
		}
		Person person=UserRepository.getInstance().getPersonObject(friendRequest.getRecipientEmail());
		errors=person.putRequest(friendRequest);
		if(!errors.isEmpty())
		{
			return errors;
		}
		UserRepository.getInstance().updateRepository(friendRequest.getRecipientEmail(), person);
		return errors;
		
	}
	
	public List<FriendRequest> viewFriendRequests(String email)
	{
		List<FriendRequest> requests=new ArrayList<>();
		Person person=UserRepository.getInstance().getPersonObject(email);
		requests=person.getFriendRequests();
		return requests;
	}
	

	
    public List<Person> getFriends(String email)
	{
		Person person=UserRepository.getInstance().getPersonObject(email);
		List<Person> friends=new ArrayList<>();
		friends=person.getFriends();
		return friends;
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

	public boolean checkIfFriends(String email, String userEmail) {
		List<Person> friends=new ArrayList<>();
		friends=getFriends(userEmail);
		if(friends==null)
		{
			return false;
		}
		for (Person person : friends) {
			if(person.getUniqueIdentifier().equals(email))
			{
				return true;
			}
		}
		return false;
	}

	public void showAllFriendRequests(String email)
	{
		
		List<FriendRequest> requests=new ArrayList<>();
		requests=viewFriendRequests(email);
		if(requests==null||requests.isEmpty())
		{
			System.out.println("You donot have any friend requests");
			processService.postActionProcess(email);
		}
		else
		{
			for (FriendRequest friendRequest : requests) {
				System.out.println(friendRequest.getpersonsEmail());
			}
			processService.processFriendRequestsMenu(email);
		}
		
	}

	public void viewAllFriends(String email) {
		List<Person> friends=new ArrayList<>();
		friends=getFriends(email);
		if(friends==null||friends.isEmpty())
		{
			System.out.println("you donot have any friends");
			processService.postActionProcess(email);
		}
		friends.forEach(friend->System.out.println(friend.toString()));
	}

	public void acceptRequest(String email, String senderEmail) {
		NotificationService notification=new NotificationService();
		String message=email+" accepted your friend request";
		Person person=UserRepository.getInstance().getPersonObject(email);
		Person friend=UserRepository.getInstance().getPersonObject(senderEmail);
		person.addFriend(friend);
		friend.addFriend(person);
		UserRepository.getInstance().updateRepository(person.getUniqueIdentifier(),person);
		UserRepository.getInstance().updateRepository(friend.getUniqueIdentifier(),friend);
		notification.putNotification(friend,message);
		System.out.println("friend request accepted");
		processService.postActionProcess(email);
	}

	public void DeclineRequest(String email, String senderEmail) {
		Person person=UserRepository.getInstance().getPersonObject(email);
		for (FriendRequest friendRequest : person.getFriendRequests()) {
			if(friendRequest.getpersonsEmail().equals(senderEmail))
			{
				person.getFriendRequests().remove(friendRequest);
				break;
			}
		}
		UserRepository.getInstance().updateRepository(email, person);
		processService.postActionProcess(email);
	}
}
