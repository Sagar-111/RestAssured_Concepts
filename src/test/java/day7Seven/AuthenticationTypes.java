package day7Seven;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

//Developer will provide all kind of information about authentication.

public class AuthenticationTypes {

	//@Test(priority=1)
	void testBasicAuth() {
	
		given()
			.auth().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")		
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true))
			.log().all();//.body()
		}
	
	//@Test(priority=2)
	void testDigestAuth() {// though the parameters are same but internal algorithm will be different.
	
		given()
			.auth().digest("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")		
		.then()
			.statusCode(200)
			.log().body();
		}
	//@Test(priority=3)
	void testPreemptiveAuth() {
	
		given()
			.auth().preemptive().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")		
		.then()
			.statusCode(200)
			.log().body();
		}
	
	//@Test(priority=4)
	void testBearerTokkenAuth() {//Widely used. And to pass with header.
	
		String bearerTokken="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";
		
		given()
			.headers("Authorization", "Bearer "+ bearerTokken)
		.when()
			.get("https://api.github.com/user/repos")		
		.then()
			.statusCode(200)
			.log().body();
		}
	
	//@Test(priority=5)
	void testAuthOneAuth() {//Many organization have upgraded from Auth 1.0 to Auth 2.0 is used.
	
		given()
			.auth().oauth("consumerkey", "consumerSecrate", "accessToken","tokenSecrate")
		.when()
			.get("URL")		
		.then()
			.statusCode(200)
			.log().body();
		}
	
	//@Test(priority=5)
	void testAuthTwoAuth() {
	
		
		given()
			.auth().oauth2("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")
		.when()
			.get("https://api.github.com/user/repos")		
		.then()
			.statusCode(200)
			.log().body();
		}
	
	@Test(priority=6)
	void testAPIKeyAuth() {//to pass with Query parameter.
	
		
		given()
			.queryParam("appid", "44b14791ee7f9330a20db22cdd95664f")
		.when()
			.get("https://api.openweathermap.org/data/2.5/forecast/daily?lat=44.34&lon=10.99&cnt=7")		
		.then()
			.log().body();
		}
}
