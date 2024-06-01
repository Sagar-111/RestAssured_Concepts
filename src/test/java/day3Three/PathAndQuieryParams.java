package day3Three;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class PathAndQuieryParams {

	// https://reqres.in/api/users?page=2&&id=5
	@Test
	
	void testPathAndQuieryParams() {
		
		given()
			.pathParam("myPath1", "api")
			.pathParam("myPath2", "users")
			.queryParam("page", 2)
			//.queryParam("id", 5)
		.when()
			.get("https://reqres.in/{myPath1}/{myPath2}")
		.then()
			.log().all();
		
	}
	
	
}
