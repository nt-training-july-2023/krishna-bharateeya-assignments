package opps;

import java.io.FileReader;
import java.util.Properties;

public class Property_Class {

	public static void main(String[] args) throws Exception{
		
		FileReader fileReader=new FileReader("d:/Assignments/src/opps/application.properties");
		
		Properties properties=new Properties();
		
		properties.load(fileReader);
		
		
		System.out.println(properties.getProperty("name"));
		System.out.println(properties.getProperty("id"));
		System.out.println(properties.getProperty("password"));
	}
}
