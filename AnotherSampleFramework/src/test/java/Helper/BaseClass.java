package Helper;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import DataProviderFactory.DataProviderFactory;
// consist of dynamic inheritance
public class BaseClass {
	// we are creating objects so we can refer back to them
	// instead of calling the jar files every time
	
	// these are global objects
	public WebDriver driver; 
	public ExtentReports report;
	public ExtentTest logger; 
	
	// user.dir goes to the lets you go to the report folder where you can get a report for each of them
	// with the correct time for that report
	// it makes it as a html format lines 30-35
	@BeforeSuite
	public void setupReport()
	{
		System.out.println("LOG:INFO- Before Suit Running - Setting up Report");
		
		ExtentHtmlReporter reporter= new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/AP_"+Utility.getTime()+".html"));
		// took the object report and made it into extent reports and the attachreporter
		// lets you know that the reports were good ready to run
		report = new ExtentReports();
		report.attachReporter(reporter);
		
		System.out.println("LOG:INFO- After Suit Running - Reports are Ready to Use");
	}
		@BeforeClass
		public void setupBrowser()
		{	// calls upon the driver to launch the browser
			// from the browser factory, get the browser, start the application
			System.out.println("LOG:INFO: Creating Browser Session");
			driver = BrowserFactories.startBrowser(
					DataProviderFactory.getConfig().getBrowser()
			,DataProviderFactory.getConfig().getStagingURL());
			System.out.println("LOG:INFO: Browser Session Created");
		}
		@AfterMethod
		// is creating a object for ITESTRESULT under appendreport
		public void appendReport(ITestResult result)
		{	// whatever result you get from the test, get the file, and save the screenshot of the file
			// will get the name of the class in which the result will be posted
			System.out.println("Test Name"+ result.getName());
			
			System.out.println("LOG:INFO-After method Running - Genereating test Report");
			// it will get the status as a int and if the status is true then its gonna print out and capture a screenshot
			// if not then get the screenshot where it failed
			int status= result.getStatus();
			// if the status passes, then it will let you know that it passes
			if(status==ITestResult.SUCCESS)
			{ 
				try{									// this allows java to save any kind of media type  // the build method is there because it is a action class
					logger.pass("Test Sceanrio Passed", MediaEntityBuilder.createScreenCaptureFromPath(Utility.captureScreenShot(driver)).build());
				// 			
				}catch(IOException e){
					System.out.println("Not able to attach Screenshots");
				}
			}
			// if it fails then get everything that happens with in the failure and take a screenshot and print it under result
			else if (status==ITestResult.FAILURE)
			{
				try{	// this attaches result			
					logger.fail("Test Failed " + result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(Utility.captureScreenShot(driver)).build());
				
				}catch(IOException e){
					System.out.println("Not able to attach screenshot");
				}
		}   // if a test case fails, then it would skip the test case and execute everything else
			else if (status==ITestResult.SKIP)
			{
				logger.skip("Test Scenario skipper");
			}
			report.flush();
			
			System.out.println("LOG:INFO- After method Executed- Test Result appened to report");
			
		}
		@AfterMethod
		public void closeSessions()
		{
			System.out.println("LOG:INFO: Closing Browser Sessions");
			driver.quit();
			System.out.println("LOG:INFO: Browser session Closed");
		}
}

