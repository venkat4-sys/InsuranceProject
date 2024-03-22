package com.ashokit.constants;

import org.springframework.stereotype.Component;

@Component
public class AppConstants {
	
	private AppConstants() {
		
	}
	
	public static final String LOGIN_FAIL="Invalid Credentials";
	
	public static final String LOGIN_SUCCESS="Login succussfully..";
	
	public static final String EMAIL_FAIL="Email not found";
	
	public static final String EMAIL_SUCCESS="mail sent successfully..";
	
	public static final String WORK_UPDATE="updated successfully";
	
	public static final String WORK_SAVE="Inserted Succuessfully...";
	
	public static final String USER_UNLOCK="Unlocked Sucessfully";
	
	public static final String USER_UNLOCK_FAIL="please check your  Credentials";
	
	public static final String UPDATE_PLAN="plan updated successfully";
	
	public static final String SAVE_PLAN="plan saved !!!";
}
