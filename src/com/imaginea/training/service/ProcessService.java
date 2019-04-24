package com.imaginea.training.service;

import java.util.Scanner;

public class ProcessService {
	static Scanner sc=new Scanner(System.in);
	static LoginService loginService=new LoginService();
	static RegistrationService registrationService=new RegistrationService();
	MenuService menuService=new MenuService();
	PostService postService=new PostService();
	UserService userService=new UserService();
	FeedService feedService=new FeedService();
	LogoutService logoutService=new LogoutService();
	NotificationService notification=new NotificationService();
	FriendService friendService=new FriendService();
	
	
	public void processInput()
	{
		int input=sc.nextInt();
		switch(input)
		{
		case 1:
			loginService.processLogin();
			break;
		case 2:
			registrationService.processRegistration();
			break;
		}
	}
	
	public void processHomeInput(String email)
	{
		int input=sc.nextInt();
			switch(input)
			{
			case 1:
				postService.makePost(email);
				break;
			case 2:
				feedService.displayOwnFeed(email);
				break;
			case 3:
				userService.viewAllUsers(email);
				break;
			case 4:
				friendService.makeRequest(email);
				break;
			case 5:
				friendService.showAllFriendRequests(email);
				break;
			case 6:
				userService.viewHomePage(email);
				break;
			case 7:
				userService.viewOwnProfile(email);
				break;
			case 8:
				userService.viewUsersProfile(email);
				break;
			case 9:
				friendService.viewAllFriends(email);
				break;
			case 10:
				notification.printNotifications(email);
				break;
			case 11:
				logoutService.logout(email);
				break;
			}
	}

	public void postActionProcess(String email)
	{
		menuService.printHomeMenu();
		processHomeInput(email);
	}
	
	public void friendRequestsMenu()
	{
		System.out.println("1.Accept request 2.Decline Request");
	}
	
	public void processFriendRequestsMenu(String email) {
		
		friendRequestsMenu();
		System.out.println("enter option");
		int option=sc.nextInt();
		System.out.println("enter email of the person");
		String senderEmail=sc.next();
		switch(option)
		{
		case 1:
			friendService.acceptRequest(email,senderEmail);
			break;
		case 2:
			friendService.DeclineRequest(email,senderEmail);
			break;
		}
		
	}
}
