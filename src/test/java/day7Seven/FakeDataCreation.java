package day7Seven;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;




public class FakeDataCreation {

	@Test
	
	void testFake() {
		
		Faker data=new Faker();
		
	String full_Name=data.name().fullName();
	String first_name=data.name().firstName();
	String last_name=data.name().lastName();
	
	String animal_name=data.animal().name();
	String phone=data.phoneNumber().cellPhone();
	
	String mail=data.internet().emailAddress();
	String pass=data.internet().password();
	
	System.out.println(full_Name);
	System.out.println(first_name);
	System.out.println(last_name);
	System.out.println(animal_name);
	System.out.println(phone);
	System.out.println(mail);
	System.out.println(pass);
	}
	
}
