package com.imaginea.training.service;

import java.util.ArrayList;
import java.util.List;

import com.imaginea.training.data.UserRepository;
import com.imaginea.training.socialnetwork.domain.Person;
import com.imaginea.training.socialnetwork.domain.Post;

public class PostService {
	FriendService friendService=new FriendService();
	
	public List<Post> getFriendsPosts(String email)
	{
		List<String> errors=new ArrayList<>();
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
	
	public List<String> addPost(Post post,String email)
	{
		List<String> errors=new ArrayList<>();
		Person p=UserRepository.getInstance().getPersonObject(email);
		p.putPosts(post);
		UserRepository.getInstance().updateRepository(email, p);
		return errors;
	}
}
