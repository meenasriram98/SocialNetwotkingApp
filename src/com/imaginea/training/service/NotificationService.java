package com.imaginea.training.service;

import com.imaginea.training.socialnetwork.domain.Notification;
import com.imaginea.training.socialnetwork.domain.Person;

public class NotificationService {
	
	
	public Person putNotification(Person person,String message)
	{
		Notification notification=new Notification(message);
		person.putNotification(notification);
		return person;
	}

}
