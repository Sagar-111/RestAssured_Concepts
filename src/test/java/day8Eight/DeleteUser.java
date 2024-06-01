package day8Eight;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {

	@Test
	
	void deleteForChaining(ITestContext context) {
		
		int id=(int) context.getAttribute("user_id");
		
		String bearerToken="e35eeb6e846f0e6512ec8ffa5828e236b9dac0082fddaa05094334b32f6c0b71";
		
		given()
			.headers("Authorization", "Bearer "+ bearerToken)
			.pathParam("id", id)
			.contentType("application/json")
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.log().all();
		
	}
}
