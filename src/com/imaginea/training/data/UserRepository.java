package com.imaginea.training.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.imaginea.training.socialnetwork.domain.FriendRequest;
import com.imaginea.training.socialnetwork.domain.Notification;
import com.imaginea.training.socialnetwork.domain.Person;
import com.imaginea.training.socialnetwork.domain.Post;

public class UserRepository {
	
	private static Map<String,Person> userData=new ConcurrentHashMap<>();
	
	private static UserRepository INSTANCE=new UserRepository();
	
	private UserRepository()
	{
		super();
	}
	
	public List<String> addRequest(FriendRequest friendRequest)
	{
		List<String> errors=new ArrayList<>();
		List<FriendRequest> requests=new ArrayList<>();
		Person person=new Person();
		person=userData.get(friendRequest.getRecipientEmail());
		errors=person.putRequest(friendRequest);
		if(!errors.isEmpty())
		{
			return errors;
		}
		userData.put(friendRequest.getRecipientEmail(), person);
		return errors;
	}
	
	public static UserRepository getInstance()
	{
		return INSTANCE;
	}
	
	public synchronized void addUser(Person person)
	{
		userData.put(person.getUniqueIdentifier(), person);
	}
	
	public synchronized List<String> addPost(Post post,String email)
	{
		List<String> errors=new ArrayList<>();
		Person p=getPersonObject(email);
		p.putPosts(post);
		userData.put(email, p);
		return errors;
		
	}
	
	public List<String> displayfeed(String email)
	{
		List<Post> posts=new ArrayList<>();
		List<String> postContent=new ArrayList<>();
		Person person=getPersonObject(email);
		posts=person.getPosts();
		if(posts==null)
		{
			return null;
		}
		posts.stream().forEach(post->postContent.add(post.getContent()));
		return postContent;
	}
	
	public List<Person> allPresentUsers()
	{
		List<Person> users=userData.values().stream().collect(Collectors.toList());
		return users;
	}
	
	public Person getPersonObject(String email)
	{
		Person person=new Person();
		person=userData.get(email);
		return person;
	}

	public List<FriendRequest> viewFriendRequests(String email) {
		Person person=getPersonObject(email);
		return person.getFriendRequests();
	}

	public boolean acceptFriendRequest(String email,String senderEmail) {
		String message=email+" accepted your friend request";
		Person person=getPersonObject(email);
		Person friend=getPersonObject(senderEmail);
		person.addFriend(friend);
		friend.addFriend(person);
		Notification notification=new Notification(message);
		friend.putNotification(notification);
		userData.put(email, person);
		userData.put(senderEmail, friend);
		return true;
	}

	public boolean declineFriendRequest(String email, String senderEmail) {
		List<FriendRequest> requests=new ArrayList<>();
		Person person=getPersonObject(email);
		requests=person.getFriendRequests();
		for (FriendRequest friendRequest : requests) {
			if(friendRequest.getpersonsEmail().equals(senderEmail))
			{
				requests.remove(friendRequest);
				break;
			}
			
		}
		return true;
	}
	
	public List<Person> returnListOfFriends(String email)
	{
		List<Person> friends=new ArrayList<>();
		Person person=getPersonObject(email);
		friends=person.getFriends();
		return friends;
	}

	
	
}
