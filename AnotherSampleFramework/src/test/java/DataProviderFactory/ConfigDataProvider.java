package DataProviderFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
//constructor
public class ConfigDataProvider {
	// have to import the properties file from selenium
	// the object pro was created for properties
	Properties pro;
	// created a constructor for the main class
	// a try catch was applied just in case the file was not found
	public ConfigDataProvider(){
		try{
			pro=new Properties();
			// load lets you load the file from the directory and and lets you access the file
			pro.load(new FileInputStream(new File(System.getProperty("user.dir")+"/ConfigurationFile/config.properties")));
		}// if it doesnt find the pro file then it will print this message 
		catch (IOException e){
			System.out.println("unable to load properties file"+e.getMessage());
		}
	}
	// created a method in order to get the browser property name and get the URL property name
	public String getBrowser()
	{
		return pro.getProperty("browser");
	}
	
	public String getStagingURL()
	{
		return pro.getProperty("stagingURL");
	}
	
	public String getValueFromProperties(String key)
	{
		return pro.getProperty(key);
	}
}
















