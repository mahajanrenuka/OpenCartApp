package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AppConstants {
/*
 * This is for adding constants
 */
	public static final String LOGIN_PAGE_TITLE = "Account Login11";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String ACCOUNT_PAGE_URL_FRACTION = "account/login";
	public static final List<String> ACCOUNT_PAGE_HEADER_LIST = Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/account";
	public static final CharSequence REGISTRATION_SUCCESS_MESSAGE = "Your Account Has Been Created!";
	public static final String  CONFIG_FILE_PATH = "./src/test/resource/Config/config.properties";
	public static final String  CONFIG_DEV_FILE_PATH = "./src/test/resource/Config/dev.properties";
	public static final String  CONFIG_QA_FILE_PATH = "./src/test/resource/Config/qa.properties";
	public static final String  CONFIG_UAT_FILE_PATH = "./src/test/resource/Config/uat.properties";
	public static final String  CONFIG_STAGE_FILE_PATH = "./src/test/resource/Config/stage.properties";

}
