package com.qa.opencart.utils;

public class StringUtils {
	
	
	public static String generateRandomEmail() {
		String emailId = "autouser"+ System.currentTimeMillis()+ "opencart.com";
		System.out.println(emailId);
		return emailId;
	}

}
