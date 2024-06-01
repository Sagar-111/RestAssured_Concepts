package day5Five;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParseXMLResponse {
	
	//Approach 1
	//@Test
	void testXMLResponse() {
			
		given()
			.contentType("application/xml; charset=utf-8")
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")	
		.then()
			.body("TravelerinformationResponse.travelers.Travelerinformation[0].id", equalTo("11133"))
			.header("X-Powered-By", equalTo("ASP.NET"))
			.log().all();
	}
	
	//Approach 2
	//@Test
	
	void testXMLResponseApproach2() {
		
		Response res=given()
			.contentType("application/xml; charset=utf-8")
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getHeader("content-Type"), "application/xml; charset=utf-8");
		Assert.assertEquals(res.xmlPath()
				.get("TravelerinformationResponse.travelers.Travelerinformation[0].id").toString(), "11133");
		Assert.assertEquals(res.xmlPath()
				.get("TravelerinformationResponse.travelers.Travelerinformation[1].name").toString(), "AS");
	}
	
	//Approach 3
		@Test
		
		void testXMLResponseApproach3() {
			
			Response res=given()
				.contentType("application/xml; charset=utf-8")
			.when()
				.get("http://restapi.adequateshop.com/api/Traveler?page=1");
			
			//By using the object method we can do different kind of operation on the data.
			XmlPath xpathObj=new XmlPath(res.asString());
			
			// Verify number of travelers in the list Use of list method.
			
			List<String> travelers=xpathObj
					.getList("TravelerinformationResponse.travelers.Travelerinformation");
			Assert.assertEquals(travelers.size(), 10);
			
			// Verify name of specific traveler in the list Use of list method.
			List<String> traveler_names=xpathObj
					.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
			
			boolean status=true;
			for (String travelernames : traveler_names) {
				
				
				if(travelernames.equals("karen")){
					status=true;
					break;
				}
			}
			
			Assert.assertEquals(status, true);
		}
}
