package com.ScriptaInsight.Testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ScriptaInsight.base.AuthorizationTokenGeneration;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DrugNdcListByDrugId {


	@Test
	public void TC01_Existing_DrugId() {
		String[] args = null;
		RestAssured.baseURI = com.ScriptaInsight.base.APIEndpoints.GetDrugNDCListBYDrugId(args);
		RequestSpecification httpRequest = RestAssured.given();
		JSONObject RequestedParameters = new JSONObject();
		httpRequest.header("Content-Type", "Application/JSON");
		httpRequest.header("clientId", "62");
		httpRequest.header("correlationId", "test");
		httpRequest.header("Authorization", AuthorizationTokenGeneration.AuthorizationkeyGeneration());
		RequestedParameters.put("drugId", 13325);
		httpRequest.body(RequestedParameters.toJSONString());
		Response response = httpRequest.request(Method.GET, "/13325/ndcs");
		
		//Validation of Status Code 
		int statuscode = response.getStatusCode();
		System.out.println("Status code is :" + statuscode);
		Assert.assertEquals(statuscode, 200);		

		if(statuscode==200) {
			Reporter.log("Get NDC List by Drug ID-Passed",true);
		}
		else {
			Reporter.log("Get NDC List by Drug ID-Failed",true);
		}
		
		//Validation of Response Body 
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		Assert.assertEquals(responseBody.contains("ndc"), true);	

	}

	@Test
	public void TC02_Non_Existing_DrugId() {
		String[] args = null;
		RestAssured.baseURI = com.ScriptaInsight.base.APIEndpoints.GetDrugNDCListBYDrugId(args);
		RequestSpecification httpRequest = RestAssured.given();
		JSONObject RequestedParameters = new JSONObject();
		httpRequest.header("Content-Type", "Application/JSON");
		httpRequest.header("clientId", "62");
		httpRequest.header("correlationId", "test");
		httpRequest.header("Authorization", AuthorizationTokenGeneration.AuthorizationkeyGeneration());		
		RequestedParameters.put("drugId", 13325);
		httpRequest.body(RequestedParameters.toJSONString());
		Response response = httpRequest.request(Method.GET, "/100000258/ndcs");
		
		int statuscode = response.getStatusCode();
		System.out.println("Get NDC List by Drug ID for Non Existing DrugID Status Code:" + statuscode);
		Assert.assertEquals(statuscode, 200);		


	}	
	@Test
	public void TC03_Invalid_DrugId() {
		String[] args = null;
		RestAssured.baseURI = com.ScriptaInsight.base.APIEndpoints.GetDrugNDCListBYDrugId(args);
		RequestSpecification httpRequest = RestAssured.given();
		JSONObject RequestedParameters = new JSONObject();
		httpRequest.header("Content-Type", "Application/JSON");
		httpRequest.header("clientId", "62");
		httpRequest.header("correlationId", "test");
		httpRequest.header("Authorization", AuthorizationTokenGeneration.AuthorizationkeyGeneration());		
		RequestedParameters.put("drugId", 13325);
		httpRequest.body(RequestedParameters.toJSONString());
		Response response = httpRequest.request(Method.GET, "/A100000258/ndcs");
		
		int statuscode = response.getStatusCode();
		System.out.println("Get NDC List by Drug ID for Invalid DrugID Status Code:" + statuscode);
		Assert.assertEquals(statuscode, 400);		


	}	
	
	@Test
	public void TC04_Invalid_Authorization_token() {
		String[] args = null;
		RestAssured.baseURI = com.ScriptaInsight.base.APIEndpoints.GetDrugNDCListBYDrugId(args);
		RequestSpecification httpRequest = RestAssured.given();
		JSONObject RequestedParameters = new JSONObject();
		httpRequest.header("Content-Type", "Application/JSON");
		httpRequest.header("clientId", "62");
		httpRequest.header("correlationId", "test");
		httpRequest.header("Authorization", "gvjhj");		
		RequestedParameters.put("drugId", 13325);
		httpRequest.body(RequestedParameters.toJSONString());
		Response response = httpRequest.request(Method.GET, "/A100000258/ndcs");
		int statuscode = response.getStatusCode();
		System.out.println("Get NDC List by Drug ID for UnAuthorized Token Status Code:" + statuscode);
		Assert.assertEquals(statuscode, 401);
		Reporter.log("Drug NDC List By Drug Id Test Run Completed");


	}
}
