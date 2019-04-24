package com.imaginea.training.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.imaginea.training.data.UserRepository;
import com.imaginea.training.socialnetwork.domain.Person;
import com.imaginea.training.socialnetwork.domain.Post;

public class PostService {
	Scanner sc=new Scanner(System.in);
	ProcessService processService=new ProcessService();
	FriendService friendService=new FriendService();
	UserInputService inputService=new UserInputService();
	FeedService feedService=new FeedService();
	
	public void makePost(String email)
	{
		String postContent=sc.next();
		Post post=new Post(postContent);
		savePost(post,email);
	}
	
	public void savePost(Post post,String email)
	{
		addPost(post, email);
		System.out.println("Post added successfully");
		processService.postActionProcess(email);
		
	}
	
	public List<Post> getFriendsPosts(String email)
	{
		List<Person> friends=friendService.getFriends(email);
		if(friends==null||friends.isEmpty())
		{
			return null;
		}
		List<Post> friendsPosts=new ArrayList<>();
		for (Person person : friends) {
			for (Post post : person.getPosts()) {
				friendsPosts.add(post);
			}
		}
		
		return friendsPosts;
	}
	
	public void addPost(Post post,String email)
	{
		Person p=UserRepository.getInstance().getPersonObject(email);
		p.putPosts(post);
		UserRepository.getInstance().updateRepository(email, p);
	}

	public void displayPosts(String email) {
		List<String> posts=new ArrayList<>();
		posts=feedService.processOwnFeed(email);
		posts.forEach(System.out::println);
	}
}
