package day3Three;

import static io.restassured.RestAssured.*;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {

	 @Test(priority=1)

	void testCookie() {

		given()

				.when().get("https://www.google.com/")
				.then()
				.cookie("AEC",
						"Ad49MVFOFR4658EpV1b4YhN_RySwHAYWukiAwrGSXP-HR6fcReyBDlG7Wg; expires=Tue, 19-Mar-2024 02:31:33 GMT; path=/; domain=.google.com; Secure; HttpOnly; SameSite=lax")
				.log().all();
		// the test should fail because cookies are dynamic, so assertion error will
		// occur.

	}

	//@Test(priority = 2)

	void testCookies() {

		Response res = given()

				.when()
					.get("https://www.google.com/");

		// For getting single cookie value.

//		String cookie_value = res.getCookie("AEC");
//
//		System.out.println("The value of AEC cookie is =" + cookie_value);
		
		//For getting all the cookies.
		
		Map<String, String> cookie_value = res.getCookies();
		
		System.out.println("All the cookie keys=="+cookie_value.keySet());
		
		for(String K:cookie_value.keySet())
		{
		System.out.println(K+"="+res.getCookie(K));
		}
		
		
	}
}
