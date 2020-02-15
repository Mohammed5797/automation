package Helper;
	import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.ie.InternetExplorerDriver;

	public class BrowserFactories {
		
		static WebDriver driver;
		
		public static  WebDriver startBrowser(String browserNa, String urls)
		{
			if(browserNa.equalsIgnoreCase("firefox"))
			{
				driver=new FirefoxDriver();
			}
			else if(browserNa.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"./Drivers/chromedriver.exe");
				driver=new ChromeDriver();
			}
			else if (browserNa.equalsIgnoreCase("IE"))
			{
				driver=new InternetExplorerDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(urls);
			return driver;
		}
		

	}



