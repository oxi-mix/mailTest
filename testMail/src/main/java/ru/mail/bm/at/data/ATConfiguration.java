package ru.mail.bm.at.data;

public interface ATConfiguration {

	public static final String USE_WAITNIGS = "main.use-wait-after-click";
	public static final String WAIT_DURATION = "main.wait-duration-ms";	
	
	//Access to the login page
	public static final String start_page = "main.start-page"; 
	public static final String portal_page = "main.portal-page";
	public static final String account_page = "main.account-page";
	
	//user details
	public static final String login = "main.login";
	public static final String pass = "main.pass";
	
	
	public static final String letter = "main.letter";
	public static final String receiver_address = "main.receiver-address";
}
