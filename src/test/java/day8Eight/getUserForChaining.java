package day8Eight;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;



public class getUserForChaining {

	@Test
	void chainingProcces2(ITestContext context) {
		
		int id=(int) context.getAttribute("user_id");
		
		String bearerToken="e35eeb6e846f0e6512ec8ffa5828e236b9dac0082fddaa05094334b32f6c0b71";
		
		given()
			.contentType("application/json")
			.headers("Authorization", "Bearer "+ bearerToken)
			.pathParam("id", id)
		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.log().all();
		
	}
	
}
