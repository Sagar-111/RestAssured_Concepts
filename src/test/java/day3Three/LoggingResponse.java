package day3Three;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class LoggingResponse {

	
	@Test
	
	void logall() {
		
		given()

		.when()
			.get("https://reqres.in/api/users/")
		.then()
			//.log().body();
			//.log().headers();
			//.log().cookies();
			.log().status();
	}
}
