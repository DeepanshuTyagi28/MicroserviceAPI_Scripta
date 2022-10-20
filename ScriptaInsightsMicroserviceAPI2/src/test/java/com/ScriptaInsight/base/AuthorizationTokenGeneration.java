package com.ScriptaInsight.base;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthorizationTokenGeneration {	
	
	public static String AuthorizationkeyGeneration() 	
	
	{	
	RestAssured.baseURI= com.ScriptaInsight.base.APIEndpoints.AuthorizationApiURI(null);
   	RequestSpecification httpRequest = RestAssured.given();
   	
     JSONObject RequestedParameters = new JSONObject(); 
     
     httpRequest.header("Content-Type","application/json");
     
     httpRequest.header("Accept","application/json");  
     
     RequestedParameters.put("userName", "scriptatest1");
     RequestedParameters.put("password", "Password9$$");
     
     httpRequest.body(RequestedParameters.toJSONString());    
     
     Response response = httpRequest.request(Method.POST,""); 
     
	 String Authorization = response.header("Authorization"); 
	 
	 return Authorization;
	 
}
}






