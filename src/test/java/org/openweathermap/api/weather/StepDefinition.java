package org.openweathermap.api.weather;

import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class StepDefinition {
	
	private String baseUrl="http://api.openweathermap.org/data/2.5";
	private String baseUrl1="http://api.openweathermap.org/data/3.0";
	private static String requestBody = "{\"external_id\":\"SF_TEST001\",\"name\":\"SanFranciscoTestStation\",\"latitude\":37.76,\"longitude\":-122.43,\"altitude\":150}";
			
		
	@Given ("User hits the weather API URI")
	public void authenticateGetRequest(){
		
		RestAssured.baseURI=baseUrl;
		System.out.println("getWeather API URI is validated successfully");
	}
	
	@When ("getWeather API finds valid API key and valid location")
	public void validateGetRequest(){
		Response res= given().header("Content-type", "application/json").param("q","London,uk").param("APPID","e188428fdda7912e4b5ea954b19c3f50").get("/weather").then().assertThat().statusCode(200).contentType(ContentType.JSON).extract().response();
		System.out.println(res.statusCode());
		System.out.println(res.contentType());
		System.out.println("getWeather API request parameters are validatated successfully");
		
	}
	
	@Then ("getWeather API provides the weather details")
	public void sendWeatherDetails(){
		Response res= given().header("Content-type", "application/json").param("q","London,uk").param("APPID","e188428fdda7912e4b5ea954b19c3f50").get("/weather").then().assertThat().statusCode(200).contentType(ContentType.JSON).extract().response();
		System.out.println(res.asString());
		System.out.println("getWeather API response sent successfully");
	}
	
	
	@Given ("User hits the registerStation API URI")
	public void authenticatePostRequest(){
		RestAssured.baseURI=baseUrl1;		
		System.out.println("registerStation API URI is validated successfully");
	}
	
	@When ("API finds valid API key and valid post request")
	public void validatePostRequest(){
		RestAssured.baseURI=baseUrl1;
		Response res= given()
				.header("Content-type", "application/json")
				.queryParam("APPID","e188428fdda7912e4b5ea954b19c3f50")
				.body(requestBody)
				.when()
				.post("/stations")
				.then()
				.assertThat()
				.statusCode(201)
				.contentType(ContentType.JSON)
				.extract()
				.response();
		System.out.println(res.statusCode());
		System.out.println(res.contentType());
		System.out.println("registerStation API request parameters are validatated successfully");
	}
	
	@Then ("registerStation API registers the station details and respond with unique ID")
	public void registerStationDetails(){
		
		Response res= given()
				.header("Content-type", "application/json")
				.queryParam("APPID","e188428fdda7912e4b5ea954b19c3f50")
				.body(requestBody)
				.when()
				.post("/stations")
				.then()
				.assertThat()
				.statusCode(201)
				.contentType(ContentType.JSON)
				.extract()
				.response();
		System.out.println(res.asString());
		System.out.println("registerStation API response sent successfully");
	}
	
	
	
	
	
	@Given ("User hits the weather API URI without API key")
	public void authenticateAPIKey(){
		RestAssured.baseURI=baseUrl;		
		System.out.println("Autentication done, invalid key");
	}
	
	@When ("getWeather API rejects the request")
	public void rejectRequest(){
		Response res= given().header("Content-type", "application/json").param("q","London,uk").get("/weather").then().assertThat().statusCode(401).contentType(ContentType.JSON).extract().response();
		System.out.println(res.statusCode());
		System.out.println("Rejected the request");
	}
	
	@Then ("getWeather API returns authentication error")
	public void sendAuthenticationError(){
		Response res= given().header("Content-type", "application/json").param("q","London,uk").get("/weather").then().assertThat().statusCode(401).contentType(ContentType.JSON).extract().response();
		System.out.println(res.statusCode());		
		System.out.println(res.asString());
		System.out.println("Authentication Error response sent successfully");
	}
}