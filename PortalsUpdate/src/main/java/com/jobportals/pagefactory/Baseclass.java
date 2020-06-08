package com.jobportals.pagefactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.jobportals.util.Util;

import io.github.bonigarcia.wdm.WebDriverManager;



public class Baseclass {

  
	public static Properties prop;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	protected WebDriver driver;

	public Baseclass() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\jobportals\\config\\Config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Initialization() {

		String path = System.getProperty("user.dir");
		if (prop.getProperty("Browser").equals("Chrome")) {
			//System.setProperty("webdriver.chrome.driver", path + "/Drivers/chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
			
		} else if (prop.getProperty("Browser").equals("Firefox")) {

			System.setProperty("webdriver.gecko.driver", path + "\\Drivers\\GeckoDriver.exe");
			driver = new ChromeDriver();

		}

	}

	public void geturl(String value) {
		driver.get(value);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
	}

	public void ReportssetUp() throws Throwable {
		// location of the extent report
		// String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new
		// Date());
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/ExtentReport.html");
		htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
		htmlReporter.config().setReportName("Functional Testing"); // Name of the report
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		// General information releated to application
		extent.setSystemInfo("Application Name", "jobportals");
		extent.setSystemInfo("User Name", "Mahesh Vaka");
		extent.setSystemInfo("Envirnoment", "QA");

	}
	
	public  void getResult(ITestResult result) throws IOException  {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent
			Util util =new Util();
		    String screenshotPath =  util.getScreenshot(driver, result.getName());
	        test.addScreenCaptureFromPath(screenshotPath);// adding screen shot
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
		}
	}

	

	public void endresult() {
		extent.flush();
		System.out.println("Extent got flushed");
	}

}
