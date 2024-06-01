package day8Eight;


import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;


import static io.restassured.RestAssured.*;


import org.json.JSONObject;



public class CreateUserForChaining {
	
	
	@Test
	void chainingProcces1(ITestContext context
			){
		
		Faker faker=new Faker();
		
		JSONObject data=new JSONObject();
		
		
		data.put("name", faker.name().fullName());
		data.put("gender", "male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		
		String bearerToken="e35eeb6e846f0e6512ec8ffa5828e236b9dac0082fddaa05094334b32f6c0b71";
				
int id= given()
			.headers("Authorization", "Bearer "+ bearerToken)
			.contentType("application/json")
			.body(data.toString( ))
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");

		System.out.println("Generated id"+id);
		 
		context.getSuite().setAttribute("user_id", id);
		}	
	
}
