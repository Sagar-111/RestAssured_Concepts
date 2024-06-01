package day8Eight;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUserForChaining {
	
	@Test 
	void putFotChaining(ITestContext context) {
		
Faker faker=new Faker();
		
		JSONObject data=new JSONObject();
		
		
		data.put("name", faker.name().fullName());
		data.put("gender", "male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		
		int id=(int) context.getAttribute("user_id");
		
		String bearerToken="e35eeb6e846f0e6512ec8ffa5828e236b9dac0082fddaa05094334b32f6c0b71";
				
		given()
			.headers("Authorization", "Bearer "+ bearerToken)
			.contentType("application/json")
			.pathParam("id", id)
			.body(data.toString( ))
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.log().all();
	}
	
	
	
	
	
	
}
