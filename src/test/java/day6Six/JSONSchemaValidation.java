package day6Six;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;




/*To validate the schema of the response, That is we are going to verify that
 *  whether Database have the desired schema(column) OR not
 *  Difference between response validation and schema validation is that we validate the data and in schema 
 *  we will focus on type of data we are getting(name(String), id(int), etc)*/
public class JSONSchemaValidation {

	
	@Test
	
	void jsonSchemaValidation() {
		
		given()
		.when()
			.get("http://localhost:3000/store")
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemaJSON.json"));
		
	}
}
