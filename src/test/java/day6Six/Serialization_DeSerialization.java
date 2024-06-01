package day6Six;





public class Serialization_DeSerialization {

	ObjectMapper omp=new ObjectMapper();
	String data_json=om.writeWithDefaultPrettyPrinter.writeValuerAsString("pojoclassobject");

	omp.readValuesFrom(jsonFile, POJOClass.class)  returns object of pojo class.

}
