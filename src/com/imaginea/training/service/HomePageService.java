package com.imaginea.training.service;

public class HomePageService {
	static MenuService menu=new MenuService();
	static ProcessService processInput=new ProcessService();
	
	public static void homePage(String email)
	{
		menu.printHomeMenu();
		processInput.processHomeInput(email);
	}

}
