package com.imaginea.training.service;

import java.util.ArrayList;
import java.util.List;

import com.imaginea.training.data.UserRepository;
import com.imaginea.training.socialnetwork.domain.Person;
import com.imaginea.training.socialnetwork.domain.Post;

public class FeedService {
	ProcessService processService=new ProcessService();
	
	public void displayOwnFeed(String email)
	{
		List<String> posts=new ArrayList<>();
		posts=processOwnFeed(email);
		posts.forEach(System.out::println);
		processService.postActionProcess(email);
	}

	public List<String> processOwnFeed(String email)
	{
		List<String> errors=new ArrayList<>();
		UserRepository repository=UserRepository.getInstance();
		List<Post> posts=new ArrayList<>();
		List<String> postContent=new ArrayList<>();
		Person person=repository.getPersonObject(email);
		if(person==null)
		{
			errors.add("no account with this email");
			return errors;
		}
		posts=person.getPosts();
		if(posts==null)
		{
			errors.add("no posts");
			return errors;
		}
		posts.stream().forEach(post->postContent.add(post.getContent()));
		return postContent;
	}
	
	public List<Post> viewusersHomePage(String email) {
		PostService postService=new PostService();
		List<Post> friendsPosts=new ArrayList<>();
		friendsPosts=postService.getFriendsPosts(email);
		return friendsPosts;
	}
}
