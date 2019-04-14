package com.imaginea.training.service;

import java.util.ArrayList;
import java.util.List;

import com.imaginea.training.data.UserRepository;
import com.imaginea.training.socialnetwork.domain.Person;
import com.imaginea.training.socialnetwork.domain.Post;

public class FeedService {
	
	public List<String> showOwnFeed(String email)
	{
		UserRepository repository=UserRepository.getInstance();
		return repository.displayfeed(email);
	}

	public List<Post> viewusersHomePage(String email) {
		List<Post> friendsPosts=new ArrayList<>();
		List<Person> friends=new ArrayList<>();
		FriendService friendService=new FriendService();
		friends=friendService.getFriends(email);
		if(friends==null||friends.isEmpty())
		{
			System.out.println("you dont have any friends to show feed");
			return null;
		}
		for (Person person : friends) {
			for (Post post : person.getPosts()) {
				friendsPosts.add(post);
			}
		}
		return friendsPosts;
		
	}
}
