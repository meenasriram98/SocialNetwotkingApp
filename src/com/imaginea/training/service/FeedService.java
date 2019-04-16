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
		List<Post> posts=new ArrayList<>();
		List<String> postContent=new ArrayList<>();
		Person person=repository.getPersonObject(email);
		posts=person.getPosts();
		if(posts==null)
		{
			return null;
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
