import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.imaginea.training.data.AuthenticationData;
import com.imaginea.training.data.UserRepository;
import com.imaginea.training.registration.Registration;
import com.imaginea.training.service.AuthenticationService;
import com.imaginea.training.service.FeedService;
import com.imaginea.training.service.FriendService;
import com.imaginea.training.socialnetwork.domain.FriendRequest;
import com.imaginea.training.socialnetwork.domain.Gender;
import com.imaginea.training.socialnetwork.domain.Notification;
import com.imaginea.training.socialnetwork.domain.Person;
import com.imaginea.training.socialnetwork.domain.PersonalInfo;
import com.imaginea.training.socialnetwork.domain.Post;

public class SocialNetwork {
	
	private static AuthenticationService authenticationService = new AuthenticationService();
	private static UserRepository usersRepository=UserRepository.getInstance();
	private static AuthenticationData authenticationData=AuthenticationData.getInstance();
	static FriendService friendService=new FriendService();
	static FeedService feedService=new FeedService();

	static Scanner sc=new Scanner(System.in);
	private static Object username;
	public static void main(String[] args) {
		printMenu();
		processInput();

	}
	
	public static void printMenu()
	{
		PersonalInfo personalInfo=new PersonalInfo("1234", "meena@gmail", "meena", "meena", "sriram", Gender.FEMALE);
		Registration registration=new Registration(personalInfo,"1234");
		register(registration,"meena@gmail","1234");
		PersonalInfo personalInfo1=new PersonalInfo("1234", "keerti@gmail", "keerti", "keerti", "sriram", Gender.FEMALE);
		Registration registration1=new Registration(personalInfo1,"1234");
		register(registration1,"keerti@gmail","1234");
		homePage("meena@gmail");
		System.out.println("choose from the following");
		System.out.println("1.Login 2.Register");
	}
	
	public static void processInput()
	{
		int input=sc.nextInt();
		switch(input)
		{
		case 1:
			processLogin();
			break;
		case 2:
			processRegistration();
			break;
		}
	}
	
	public static void processLogin()
	{
		System.out.println("Login process is initiated. Please provide below details");
		System.out.println("Email:");
		String email = sc.next();
		System.out.println("Password:");
		String password = sc.next();
		
		if (authenticationService.authenticate(email, password)) {
			
			homePage(email);

		} else
		{
			System.out.println("Login is failed");
		}
		
		
	}
	
	public static void homePage(String email)
	{
		printHomeMenu();
		processHomeInput(email);
	}
	
	public static void printHomeMenu()
	{
		System.out.println("1.make post  2.Display my feed  3.View all users present  4.send friend request  5.show all requests 6.view home page 7.view your profile 8.view all friends");
	}
	
	public static void processHomeInput(String email)
	{
		int input=sc.nextInt();
			switch(input)
			{
			case 1:
				makePost(email);
				break;
			case 2:
				displayOwnFeed(email);
				break;
			case 3:
				viewAllUsers(email);
				break;
			case 4:
				makeRequest(email);
				break;
			case 5:
				showAllFriendRequests(email);
				break;
			case 6:
				viewHomePage(email);
				break;
			case 7:
				viewOwnProfile(email);
				break;
			case 8:
				viewUsersProfile(email);
				break;
			case 9:
				viewAllFriends(email);
				break;
			case 10:
				printNotifications(email);
			}
		
		
	}
	
	private static void printNotifications(String email) {
		Person p=UserRepository.getInstance().getPersonObject(email);
		List<Notification> notifications=p.getNotifications();
		if(notifications!=null)
		{
			notifications.forEach(System.out::println);
			printHomeMenu();
			processHomeInput(email);
		}
		System.out.println("you donot have any notifications");
		
	}

	private static void viewAllFriends(String email) {
		List<Person> friends=new ArrayList<>();
		friends=friendService.getFriends(email);
		if(friends==null||friends.isEmpty())
		{
			System.out.println("you donot have any friends");
			printHomeMenu();
			processHomeInput(email);
		}
		friends.forEach(friend->System.out.println(friend.toString()));
	}

	private static void viewUsersProfile(String email) {
		System.out.println("enter email of the person");
		String userEmail=sc.next();
		if(checkIfFriends(email,userEmail))
		{
			viewOwnProfile(userEmail);
			displayPosts(userEmail);
		}
		else
		{
			viewInfo(userEmail);
		}
	}

	private static void viewInfo(String userEmail) {
		Person p=UserRepository.getInstance().getPersonObject(userEmail);
		System.out.println(p.BasicInfo());
		
	}

	private static void displayPosts(String email) {
		List<String> posts=new ArrayList<>();
		posts=feedService.showOwnFeed(email);
		posts.forEach(System.out::println);
	}

	private static boolean checkIfFriends(String email, String userEmail) {
		List<Person> friends=new ArrayList<>();
		friends=friendService.getFriends(userEmail);
		if(friends==null)
		{
			return false;
		}
		for (Person person : friends) {
			if(person.getUniqueIdentifier().equals(email))
			{
				return true;
			}
		}
		return false;
	}

	private static void viewHomePage(String email) {
		List<Post> friendsPosts=new ArrayList<>();
		friendsPosts=feedService.viewusersHomePage(email);
		if(friendsPosts==null)
		{
			printHomeMenu();
			processHomeInput(email);
		}
		friendsPosts.forEach(System.out::println);
	}

	public static void friendRequestsMenu()
	{
		System.out.println("1.Accept request 2.Decline Request");
	}
	
	public static void showAllFriendRequests(String email)
	{
		
		List<FriendRequest> requests=new ArrayList<>();
		requests=friendService.viewFriendRequests(email);
		if(requests==null||requests.isEmpty())
		{
			System.out.println("You donot have any friend requests");
			printHomeMenu();
			processHomeInput(email);
		}
		else
		{
			for (FriendRequest friendRequest : requests) {
				System.out.println(friendRequest.getpersonsEmail());
			}
			friendRequestsMenu();
			processFriendRequestsMenu(email);
		}
		
	}
	
	private static void processFriendRequestsMenu(String email) {
		System.out.println("enter option");
		int option=sc.nextInt();
		System.out.println("enter email of the person");
		String senderEmail=sc.next();
		switch(option)
		{
		case 1:
			acceptRequest(email,senderEmail);
			break;
		case 2:
			DeclineRequest(email,senderEmail);
			break;
		}
		
	}

	private static void DeclineRequest(String email, String senderEmail) {
		if(friendService.declineRequest(email,senderEmail))
		{
			System.out.println("request deleted");
		}
	}

	private static void acceptRequest(String email, String senderEmail) {
		if(friendService.acceptRequest(email, senderEmail))
		{
			System.out.println("friend request accepted");
		}
		printHomeMenu();
		processHomeInput(email);
	}

	public static void makeRequest(String email)
	{
		System.out.println("enter email of the person");
		String requestEmail=sc.next();
		if(!isUsernameAlreadyExists(requestEmail))
		{
			System.out.println("enter correct email");
			printHomeMenu();
			processHomeInput(email);
		}
		FriendRequest friendRequest=new FriendRequest(email,requestEmail);
		sendFriendRequest(friendRequest,email);
	}
	
	public static void sendFriendRequest(FriendRequest friendRequest,String email)
	{
		
		List<String> errors=new ArrayList<>();
		errors=friendService.sendFriendRequest(friendRequest);
		if(!errors.isEmpty())
		{
			errors.forEach(System.out::println);
		}
		else
		{
			System.out.println("Friend request sent successfully");
			processLogin();
		}
		printHomeMenu();
		processHomeInput(email);
		
	}
	
	public static void viewAllUsers(String email)
	{
		//List<String> mutualFriends=new ArrayList<>();
		List<Person> presentUsers=new ArrayList<>();
		presentUsers=usersRepository.allPresentUsers();
		presentUsers.forEach(person->{if(!person.getUniqueIdentifier().equals(email)) {
			System.out.println(person.BasicInfo());
			printMutualFriends(email,person.getUniqueIdentifier());}
		});
		printHomeMenu();
		processHomeInput(email);
	}
	
	private static void printMutualFriends(String email, String personsEmail) {
		List<String> mutualFriends=new ArrayList<>();
		mutualFriends=friendService.getMutualFriends(email,personsEmail);
		if(mutualFriends!=null)
		{
			mutualFriends.forEach(System.out::println);
		}
		
		
	}

	public static void viewOwnProfile(String email)
	{
		Person person=usersRepository.getPersonObject(email);
		System.out.println(person.toString());
		System.out.println("Posts made: ");
		displayOwnFeed(email);
		
		printHomeMenu();
		processHomeInput(email);
	}
	
	public static void displayOwnFeed(String email)
	{
		List<String> posts=new ArrayList<>();
		posts=feedService.showOwnFeed(email);
		if (posts==null||posts.isEmpty()) {
			System.out.println("havent made any posts yet");
			printHomeMenu();
			processHomeInput(email);
			
		}
		else {
			posts.forEach(System.out::println);
			return;
		}
		
	}
	
	public static void makePost(String email)
	{
		System.out.println("enter text:");
		String postContent = sc.next();
		Post post=new Post(postContent);
		savePost(post,email);
	}
	
	public static void savePost(Post post,String email)
	{
		List<String> errors=new ArrayList<>();
		errors=usersRepository.addPost(post, email);
		
		if (!errors.isEmpty()) {
			errors.forEach(System.out::println);
		}
		else
		{
			System.out.println("Post added successfully");
			printHomeMenu();
			processHomeInput(email);
		}
		printHomeMenu();
		processHomeInput(email);
	}
	
	public static void processRegistration()
	{
		System.out.println("Registration process is initiated. Please provide below details");
		System.out.println("Phone:");
		String phone = sc.next();
		System.out.println("E-Mail:");
		String email = sc.next();
		System.out.println("firstName:");
		String firstName = sc.next();
		System.out.println("lastName:");
		String lastName = sc.next();
		System.out.println("Gender M/F:");
		String userGenderInput = sc.next();

		Gender gender = Gender.MALE;
		if (userGenderInput.equals("F"))
			gender = Gender.FEMALE;

//		System.out.println("Date of Birth (dd/mm/YYYY):");
//		String dob = sc.next();

		System.out.println("Username:");
		String username = sc.next();

		System.out.println("password:");
		String password = sc.next();

//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
//		Date dateOfBirth = null;
		
//		try {
//			dateOfBirth = dateFormat.parse(dob);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		PersonalInfo personalInfo=new PersonalInfo(phone, email, username, firstName, lastName, gender);
		Registration registration=new Registration(personalInfo,password);
		register(registration,username,password);
		
	}
	
	private static void register(Registration registration,String email,String password)
	{
		List<String> errors=validate(registration);
		
		if (!errors.isEmpty()) {
			errors.forEach(System.out::println);
			return;
		}
		saveToRepository(registration);
		storeUserCredentials(email, password);
		System.out.println("Account created successfully!!");


	}
	
	private void logout(String username) {
		if (authenticationService.logout(username))
			System.out.println("Logout is successful!");
		else
			System.out.println("Logout is failed!");

	}
	
	public static List<String> validate(Registration registration)
	{
		List<String> errors = new ArrayList<>();
	if (isUsernameAlreadyExists(registration.getPersonalInfo().getUsername()))
		errors.add("Username already exists");

	if (isEmailAlreadyExists(registration.getPersonalInfo().getEmail()))
		errors.add("Email already exists");

	return errors;

		
	}
	
	private static boolean isEmailAlreadyExists(String email) {
		return false;
	}

	private static boolean isUsernameAlreadyExists(final String username) {
		return authenticationData.isUserNameAlreadyExists(username);
	}
	
	public static UserRepository saveToRepository(Registration register)
	{
		
		usersRepository.addUser(new Person(register));
		return usersRepository;
		
	}
	
	private static void storeUserCredentials(String email, String password) {
		authenticationData.store(email, password);
	}

}


