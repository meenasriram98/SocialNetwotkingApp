package com.imaginea.training.data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationData {
	
	
	
	private static String MD5="MD5";
	private Map<String,String> userCredentialsMap=new HashMap<>();
	
    private static AuthenticationData INSTANCE=new AuthenticationData();
 	
	private AuthenticationData()
	{
		super();
	}
	
	public static AuthenticationData getInstance()
	{
		return INSTANCE;
	}
	
	public void store(final String username,final String password)
	{
		userCredentialsMap.put(username, encrypt(password));
	}
	
	private String encrypt(String password)
	{
		MessageDigest digest=null;
		try {
			digest=MessageDigest.getInstance(MD5);
			byte[] Array=digest.digest(password.getBytes());
			return new String(Array);
		}
		catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return password;
	}
	
	public boolean isUserNameAlreadyExists(String userName)
	{
		return userCredentialsMap.containsKey(userName);
	}
	
	public boolean isUserNamePasswordMatching(final String userName,final String password)
	{
		if (isUserNameAlreadyExists(userName))
		{
			return encrypt(password).equals(userCredentialsMap.get(userName));
		}
		return false;
	}

}
