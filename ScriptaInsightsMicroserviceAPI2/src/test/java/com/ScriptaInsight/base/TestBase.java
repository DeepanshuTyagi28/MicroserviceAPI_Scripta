package com.ScriptaInsight.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONObject;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class TestBase {


	public static String DBConnection(String[] args) {
		String DBConnection = "https://member-app-services-int-pipeline.scriptainsights.io/scripta-insights/member-app-api/0.1/member/authenticate/";
		return DBConnection;
	}

	public static int ClientId(String[] args) {
	    int ClientId = 62;
		return ClientId;

	}
		
	public static int DrugId(String[] args) {
	    int DrugId = 13325;
		return DrugId;
	}
	
	public static String MailFrom(String[] args) {
		String Mailfrom = "scriptainsight@gmail.com";
		return Mailfrom;
	}
	
	public static String MailTO(String[] args) {
		String MailTO = "scriptainsight@gmail.com";
		return MailTO;
	}
	
	public static String Mail_CC(String[] args) {
		String Mail_CC = "scriptainsight@gmail.com";
		return Mail_CC;
	}
	
	public static String EmailEncryptedUsername(String[] args) {
		String Email_Username = "scriptainsight@gmail.com";
		return Email_Username;
	}
	
	public static String EmailEncryptedPassword(String[] args) {
		String Email_Password = "mbjnletlrvuztewx";
		return Email_Password;
	}

}
