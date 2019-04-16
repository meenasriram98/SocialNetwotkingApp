package com.imaginea.training.data;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.imaginea.training.socialnetwork.domain.Person;

public class UserRepository {
	
	private static Map<String,Person> userData=new ConcurrentHashMap<>();
	
	private static UserRepository INSTANCE=new UserRepository();
	
	private UserRepository()
	{
		super();
	}
	
	
	public static UserRepository getInstance()
	{
		return INSTANCE;
	}
	
	public synchronized void addUser(Person person)
	{
		userData.put(person.getUniqueIdentifier(), person);
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
	
	public void updateRepository(String key,Person value)
	{
		userData.put(key, value);
	}
	
	
}
