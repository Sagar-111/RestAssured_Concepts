package day4Four;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class ParsingJSONResponseData {

	// @Test
	void testJSONRes() {

		/*
		 * //APPROACH 1 given() .contentType("application/json") .when()
		 * .get("http://localhost:3000/store") .then() .body("book[4].Title",
		 * equalTo("Becoming")); the assertions we made here comes from matchers package
		 * this method of validation is not suitable for large data and have limited
		 * scope So parsing the response in the variable is most suitable and versatile.
		 * For that remove then section and assign a variable to Response body
		 */

		// APPROACH 2 TestNG assertions
		/*
		 * Response res=given() .contentType(ContentType.JSON) .when()
		 * .get("http://localhost:3000/store");
		 * 
		 * Assert.assertEquals(res.getStatusCode(), 200);
		 * Assert.assertEquals(res.header("Content-Type"),
		 * "application/json; charset=utf-8");
		 * 
		 * String bookName=res.jsonPath().get("book[3].Title").toString();
		 * Assert.assertEquals(bookName, "Harry Potter and the Sorcerer's Stone");
		 */
	}

	//APPROACH 2
	//By using the object method we can do different kind of operation on the data.
	
	@Test
	void testJSONResData() {

		Response res = given().contentType(ContentType.JSON).when().get("http://localhost:3000/store");

		JSONObject jo = new JSONObject(res.asString()); // converting res to json object type.
		// had made mistake by taking .toString() while parsing take care instead it is .asString().

		for (int i = 0; i < jo.getJSONArray("book").length(); i++) {
			String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("Title").toString();
			System.out.println("Titles of books  " + bookTitle);
		}

		for (int i = 0; i < jo.getJSONArray("novels").length(); i++) {
			String novelTitle = jo.getJSONArray("novels").getJSONObject(i).get("Title").toString();
			System.out.println("Titles of novel  " + novelTitle);
		}
		
		double totalPrice=0;
		for(int i=0; i<jo.getJSONArray("book").length();i++) {
			String bookPrice=jo.getJSONArray("book").getJSONObject(i).get("Price").toString();
			
			totalPrice=totalPrice + Double.parseDouble(bookPrice);
			
		}
		System.out.println("Total price of books is ="+totalPrice);
	}

}
