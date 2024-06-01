package day3Three;

import static io.restassured.RestAssured.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeaderDemo {
	
	
	private static final int Content = 0;

	//@Test
	
	void testHeader() {
		
		given()

		.when()
			.get("https://www.google.com/")
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.header("Content-Encoding", "gzip")
			.log().all();		
	}
	
	@Test
	
		void testHeaders() {
		
	Response res=given()

		.when()
			.get("https://www.google.com/");
		//For single header value.
		String header_value=res.getHeader("Content-Type");
		
		System.out.println("Content-Type="+header_value);
		
		
		//For all header value.
		
		Headers header_values=res.getHeaders();
		
		for(Header H:header_values) 
		{
			System.out.println(H.getName()+"="+H.getValue());
		}
	}
}
