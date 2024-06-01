package day2Two;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class waysToCreatePostBody {
	
	//1--creating body using HashMap, Only for small data.

	
	@Test(priority=1)
	
	void postByHM() {
		
		HashMap<String, Object> data=new HashMap<String, Object>(); 
		
		data.put("phone", "97030145151");
		data.put("name", "navnath");
		data.put("location", "sambhajinagar");
		
		String[] courseArr= {"java", "C#"};
		
		data.put("course", courseArr);
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/student")
		.then()
			.statusCode(201)
			.body("name", equalTo("navnath"))
			.body("location", equalTo("sambhajinagar"))
			.body("phone", equalTo("97030145151"))
			.body("course[0]", equalTo("java"))
			.body("course[1]", equalTo("C#"))
			.contentType("application/json; charset=utf-8");
	}
	
	//2--creating body using org.json for this add json (not json-path)dependency to pom. 
	
	//@Test(priority=1)
	
	void postByJsonOrg() {
		
		JSONObject data=new JSONObject();//JSONObject 
		
		data.put("name", "navnath");
		data.put("phone", "97030145151");
		data.put("location", "sambhajinagar");
		
		String courseArr[]= {"java", "C#"};
		
		data.put("course", courseArr);
		
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/student")
		.then()
			.statusCode(201)
			.body("name", equalTo("navnath"))
			.body("location", equalTo("sambhajinagar"))
			.body("phone", equalTo("97030145151"))
			.body("course[0]", equalTo("java"))
			.body("course[1]", equalTo("C#"))
			.contentType("application/json; charset=utf-8");
	}
	
	// 3. Creating post request by Plain Old Java Object(POJO)
	
	//@Test (priority=1)
	
	void postByPOJO() {
		
		POJOForPost data=new POJOForPost();
		
		data.setName("Navnath");
		data.setLocation("Sambhajinagar");
		data.setPhone("97030145151");
		
		String courseArr[]= {"java", "C#"};
		data.setCourse(courseArr);
		
	given()
		.contentType("application/json")
		.body(data)
	.when()
		.post("http://localhost:3000/student")
	.then()
		.statusCode(201)
		.body("name", equalTo("Navnath"))
		.body("location", equalTo("Sambhajinagar"))
		.body("phone", equalTo("97030145151"))
		.body("course[0]", equalTo("java"))
		.body("course[1]", equalTo("C#"))
		.contentType("application/json; charset=utf-8")
		.log().all();
		
	}
	
	
	// 4. creating post body using external json file.
	
	//@Test(priority=1)
	
	void postByExternalJsonFile() throws FileNotFoundException {
		// open file
		File f=new File(".\\body.json");
		// Or you can use .\\body.json (.\\) show same workspace.
		
		// Read file data, don't forgot to pass argument, shows file to read.
		FileReader fr=new FileReader(f);
		// import all this packages from java.io carefully.
		
		//Create token of data of json file.
		JSONTokener jt=new JSONTokener(fr);  //import from org.json
		
		JSONObject data=new JSONObject(jt);// throws exception just add it.
		
		//No need to map fields just keep the file open.
		
		given()
		.contentType("application/json")
		.body(data.toString())
	.when()
		.post("http://localhost:3000/student")
	.then()
		.statusCode(201)
		.body("name", equalTo("Navnath"))
		.body("location", equalTo("Sambhajinagar"))
		.body("phone", equalTo("97030145151"))
		.body("course[0]", equalTo("java"))
		.body("course[1]", equalTo("C#"))
		.contentType("application/json; charset=utf-8")
		.log().all();
	}
	
@Test (priority=2, dependsOnMethods="postByHM")
	
	void deleteUserCreated() {
		
		when()
			.delete("http://localhost:3000/student/10")
		.then()
			.log().all();
		
	}
	
}
