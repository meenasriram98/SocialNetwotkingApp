package com.imaginea.training.socialnetwork.domain;

import java.util.ArrayList;
import java.util.List;

import com.imaginea.training.registration.Registration;

public class Person {
	
	private PersonalInfo personalInfo;
	 private Address address;
	 
	 private List<String> intrests;
	 private List<String> hobbies;
	 private List<Person> friends;
	 private List<Post> posts;
	 private List<AcademicInfo> academicDetails;
	 private List<PersonWorkExperience> workDetails;
	 private List<FriendRequest> friendRequests;
	 private List<Notification> notifications;
	 
	 public Person(Registration registration)
	 {
		 this.personalInfo=registration.getPersonalInfo();
	 }
	 
	 public Person() {
		 
	 }
	 
	 
	 public String getUniqueIdentifier()
	 {
		 return personalInfo.getEmail();
	 }
	 
	 public List<Post> getPosts()
	 {
		 return this.posts;
	 }
	 
	 public List<FriendRequest> getFriendRequests()
	 {
		 return this.friendRequests;
	 }
	 
	 public void putPosts(Post post)
	 {
		 if(this.posts==null)
		 {
			 this.posts=new ArrayList<>();
		 }
		 posts.add(post);
		 
	 }
	 
	 public List<Person> getFriends()
	 {
		 return this.friends;
	 }
	 
	 public void addFriend(Person friend)
	 {
		 if(this.friends==null)
		 {
			 this.friends=new ArrayList<>();
		 }
		 this.friends.add(friend);
	 }
	 
	 public List<Notification> getNotifications()
	 {
		 return this.notifications;
	 }
	 
	 public void putNotification(Notification notification)
	 {
		 if(this.notifications==null)
		 {
			 this.notifications=new ArrayList<>();
		 }
		 notifications.add(notification);
		 
	 }
	 
	 public List<String> putRequest(FriendRequest request)
	 {
		 List<String> errors=new ArrayList<>();
		 if(this.friendRequests==null)
		 {
			 this.friendRequests=new ArrayList<>();
		 }
		 if(this.friendRequests.contains(request))
		 {
			 errors.add("request already exists");
		 }
		 friendRequests.add(request);
		 return errors;
	}
	 
	 
	 public void addFriendRequest(FriendRequest request)
	 {
		if(this.friendRequests.isEmpty())
		{
			this.friendRequests=new ArrayList<>();
		}
		friendRequests.add(request);
	 }
	 
	 @Override
		public String toString() {
			return   "Name=" + personalInfo.getUsername() + ", email=" + getUniqueIdentifier()
					+ ", username" + personalInfo.getUsername() + ", phone number" + personalInfo.getNumber();
	 }
	 
	 public String BasicInfo()
	 {
		 return   "Name=" + personalInfo.getUsername() + ", email=" + getUniqueIdentifier()
			+ ", username" + personalInfo.getUsername();
	 }
	 
	 public String getOtherInfo()
	 {
		 return "Academic details:"+this.academicDetails+" work details: "+this.workDetails+" Hobbies: "+this.hobbies;
	 }
	 
}