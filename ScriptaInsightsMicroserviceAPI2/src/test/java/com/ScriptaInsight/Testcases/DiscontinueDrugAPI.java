package com.ScriptaInsight.Testcases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.ScriptaInsight.base.AuthorizationTokenGeneration;
import com.ScriptaInsight.base.DataBaseConnection;
import com.ScriptaInsight.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DiscontinueDrugAPI {

	@BeforeTest

	public void DeleteDrugid() throws SQLException {
		DataBaseConnection.DeleteDrugid();
	}
	
	@Test(priority=1)
	public void TC01_Valid_DrugId() {
		String[] args = null;
		RestAssured.baseURI = com.ScriptaInsight.base.APIEndpoints.DiscontinueDrugURI(args);
		RequestSpecification httpRequest = RestAssured.given();
		JSONObject RequestedParameters = new JSONObject();
		httpRequest.header("Content-Type", "Application/JSON");
		httpRequest.header("clientId", com.ScriptaInsight.base.TestBase.ClientId(args));
		httpRequest.header("correlationId", "test");
		httpRequest.header("Authorization", AuthorizationTokenGeneration.AuthorizationkeyGeneration());
		RequestedParameters.put("drugId", com.ScriptaInsight.base.TestBase.DrugId(args));
		httpRequest.body(RequestedParameters.toJSONString());
		Response response = httpRequest.request(Method.POST, "");		
		//Validation of Response Code
		int statuscode = response.getStatusCode();
		System.out.println("Status code of Drug Discontinue:"+ statuscode);
		Assert.assertEquals(statuscode, 201);
		if(statuscode==201) {
			Reporter.log("Drug Discontinue-Passed",true);
		}
		else {
			Reporter.log("Drug Discontinue-Failed",true);
		}
		
		//Validation of Response Body 
		String responseBody = response.getBody().asString();
		System.out.println("Response Body for Discontinue Drug"+ responseBody);
		Assert.assertEquals(responseBody.contains("discontinuedItemId"), true);	
		
		//Validation of Response Time 
		int ResponseTime = (int) response.getTime();
		System.out.println("Response Time for Drug Discontinue is:"+ResponseTime);
		Assert.assertEquals(response.getTimeIn(TimeUnit.MILLISECONDS)<1000,true);
		if(ResponseTime>1000) {
			Reporter.log("Response Time is greater than expected" , true);
		}

	}
	
	@Test(priority=2)
	public void TC02_Existing_Discontinued_Drug() {
		String[] args = null;
		RestAssured.baseURI = com.ScriptaInsight.base.APIEndpoints.DiscontinueDrugURI(args);
		RequestSpecification httpRequest = RestAssured.given();
		JSONObject RequestedParameters = new JSONObject();
		httpRequest.header("Content-Type", "Application/JSON");
		httpRequest.header("clientId", com.ScriptaInsight.base.TestBase.ClientId(args));
		httpRequest.header("correlationId", "test");
		httpRequest.header("Authorization", AuthorizationTokenGeneration.AuthorizationkeyGeneration());
		RequestedParameters.put("drugId", com.ScriptaInsight.base.TestBase.DrugId(args));
		httpRequest.body(RequestedParameters.toJSONString());
		Response response = httpRequest.request(Method.POST, "");
		int statuscode = response.getStatusCode();
		System.out.println("Status code for Existing Discontinued Drug:" + statuscode);
		Assert.assertEquals(statuscode, 409);
	}
	
	@Test(priority=3)
	public void TC03_Invalid_DrugId() {
		String[] args = null;
		RestAssured.baseURI = com.ScriptaInsight.base.APIEndpoints.DiscontinueDrugURI(args);
		RequestSpecification httpRequest = RestAssured.given();
		JSONObject RequestedParameters = new JSONObject();
		httpRequest.header("Content-Type", "Application/JSON");
		httpRequest.header("clientId", com.ScriptaInsight.base.TestBase.ClientId(args));
		httpRequest.header("correlationId", "test");
		httpRequest.header("Authorization", AuthorizationTokenGeneration.AuthorizationkeyGeneration());
		RequestedParameters.put("drugId", 14525);
		httpRequest.body(RequestedParameters.toJSONString());
		Response response = httpRequest.request(Method.POST, "");
		int statuscode = response.getStatusCode();
		System.out.println("Status Code when Invalid Drug Id Passed :" + statuscode);
		Assert.assertEquals(statuscode, 400);
	}
	
	@Test(priority=4)
	public void TC04_Invalid_Authorization_Token() {
		String[] args = null;
		RestAssured.baseURI = com.ScriptaInsight.base.APIEndpoints.DiscontinueDrugURI(args);
		RequestSpecification httpRequest = RestAssured.given();
		JSONObject RequestedParameters = new JSONObject();
		httpRequest.header("Content-Type", "Application/JSON");
		httpRequest.header("clientId", com.ScriptaInsight.base.TestBase.ClientId(args));
		httpRequest.header("correlationId", "test");
		httpRequest.header("Authorization", "hdbkhabkahbkj");
		RequestedParameters.put("drugId", 13325);
		httpRequest.body(RequestedParameters.toJSONString());
		Response response = httpRequest.request(Method.POST, "");
		int statuscode = response.getStatusCode();
		System.out.println("Authorization Token Expired with the Status Code: " + statuscode);
		Assert.assertEquals(statuscode, 401);
	}
}
