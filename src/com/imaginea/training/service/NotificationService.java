package com.imaginea.training.service;

import java.util.List;

import com.imaginea.training.data.UserRepository;
import com.imaginea.training.socialnetwork.domain.Notification;
import com.imaginea.training.socialnetwork.domain.Person;

public class NotificationService {
	
	ProcessService processService=new ProcessService();
	
	public void printNotifications(String email) {
		Person p=UserRepository.getInstance().getPersonObject(email);
		List<Notification> notifications=p.getNotifications();
		if(notifications!=null)
		{
			for (Notification notification : notifications) {
				System.out.println(notification.getNotification());
			}
			processService.postActionProcess(email);
		}
		System.out.println("you donot have any notifications");
		
	}
	
	public Person putNotification(Person person,String message)
	{
		Notification notification=new Notification(message);
		person.putNotification(notification);
		return person;
	}

}
