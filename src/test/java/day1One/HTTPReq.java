package day1One;
/*Methods--
  	given()
 		Authentication, parameters, cookies, headers,
 	when()
 		Type of request.
 	Then()
 		all validation.*/
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPReq {
	int id;

	@Test(priority=1)
	void getUsers() {
		
		given()
		.when()
			.get("https://reqres.in/api/users?page=1")
		.then()
			.statusCode(200)
			.body("page",equalTo(1))
			.log().all();
		
	}
	
	@Test(priority=2)
	void createUser() {
		
		HashMap data=new HashMap();
		
		
		data.put("name", "sagar");
		data.put("job", "Tester");
		
  int id=given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users/")
			.jsonPath().getInt("id");   //to catch the response you must write jsonpath().
//		.then()
//			.statusCode(201)
//			.log().all();
		System.out.println(id);
	}
	
	
	@Test(priority=3, dependsOnMethods="createUser")
	void updateUser(){
		
		HashMap data=new HashMap();
		
		
		data.put("name", "Adhirat");
		data.put("job", "vorior");
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.put("https://reqres.in/api/users/" + id )
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority=4, dependsOnMethods="updateUser")
	void deleteUser(){
//		
//		HashMap data=new HashMap();
//		
//		
//		data.put("name", "Adhirat");
//		data.put("job", "vorior");
//		
//		given()
//			.contentType("application/json")
//			.body(data)
		when()
			.delete("https://reqres.in/api/users/" + id)
		.then()
			.statusCode(204)
			.log().all();
		
	}
	
	
}
