package com.imaginea.training.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.imaginea.training.data.UserRepository;
import com.imaginea.training.socialnetwork.domain.Person;
import com.imaginea.training.socialnetwork.domain.Post;

public class UserService {
	
	Scanner sc=new Scanner(System.in);
	UserRepository usersRepository=UserRepository.getInstance();
	FriendService friendService=new FriendService();
	ProcessService processService=new ProcessService();
	FeedService feedService=new FeedService();
	UserInputService inputService=new UserInputService();
	PostService postService=new PostService();
	
	public void viewAllUsers(String email)
	{
		//List<String> mutualFriends=new ArrayList<>();
		List<Person> presentUsers=new ArrayList<>();
		presentUsers=usersRepository.allPresentUsers();
		presentUsers.forEach(person->{if(!person.getUniqueIdentifier().equals(email)) {
			System.out.println(person.BasicInfo());
			System.out.println(person.getOtherInfo());
			friendService.printMutualFriends(email,person.getUniqueIdentifier());}
		});
		processService.postActionProcess(email);
	}

	public void viewHomePage(String email) {
		List<Post> friendsPosts=new ArrayList<>();
		friendsPosts=feedService.viewusersHomePage(email);
		if(friendsPosts==null)
		{
			processService.postActionProcess(email);
		}
		for (Post post : friendsPosts) {
			System.out.println(post.getContent());
		}
	}
	
	public void viewOwnProfile(String email)
	{
		Person person=usersRepository.getPersonObject(email);
		System.out.println(person.toString());
		System.out.println("Posts made: ");
		feedService.displayOwnFeed(email);
		
		processService.postActionProcess(email);
	}

	public void viewUsersProfile(String email) {
		System.out.println("enter email of the person");
		String userEmail=sc.next();
		if(friendService.checkIfFriends(email,userEmail))
		{
			viewOwnProfile(userEmail);
			postService.displayPosts(userEmail);
		}
		else
		{
			viewInfo(userEmail);
		}
	}

	public void viewInfo(String userEmail) {
		Person p=UserRepository.getInstance().getPersonObject(userEmail);
		System.out.println(p.BasicInfo());
		
	}

}
