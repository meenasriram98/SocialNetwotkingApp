package com.imaginea.training.socialnetwork.domain;

import java.sql.Date;

public class FriendRequest {
	
	private String personsEmail;
	private String recipientEmail;
	
	public FriendRequest(String senderEmail,String recipientEmail)
	{
		this.personsEmail=senderEmail;
		this.recipientEmail=recipientEmail;
	}
	
	public String getRecipientEmail()
	{
		return this.recipientEmail;
	}
	
	public String getpersonsEmail()
	{
		return this.personsEmail;
	}
}
