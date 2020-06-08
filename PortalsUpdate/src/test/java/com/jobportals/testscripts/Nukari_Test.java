package com.jobportals.testscripts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.jobportals.pagefactory.Baseclass;
import com.jobportals.pagefactory.Nukari_Page;
import com.jobportals.util.Util;

public class Nukari_Test extends Baseclass {
	private Nukari_Page nukari;
	private Util util;
	
public Nukari_Test() {
	super();
}

@BeforeSuite
public void generatereports() throws Throwable {
	ReportssetUp();
}

@BeforeClass
public void setup() {
	Initialization();
	geturl(prop.getProperty("url1"));
	nukari = new Nukari_Page(driver);
//	util = new Util();
//	util.closechildwindows();
	
}

@Test(priority=1, enabled = true)
public void nukarivlidatesitename() {
	test = extent.createTest("nukarivlidatesitename");
	String sitename = nukari.sitename();
	System.out.println("pass 1 method");
	Assert.assertEquals(sitename, "Jobs - Recruitment - Job Search - Employment -Job Vacancies - Naukri.com");
}

@Test (priority=2, enabled = true)
public void nukarivalidlogin() {
	test = extent.createTest("nukarivalidlogin");
	String siteurl = nukari.validlogin();
	if (siteurl.contains("homepage"))
		Assert.assertTrue(true);
	else
		Assert.assertTrue(false);
}

@Test(priority=3, enabled = true)
public void nukariupdateresume() throws Throwable {
	test = extent.createTest("nukariupdateresume");
	String updatedstamp = nukari.updateresume();
	String timestamp = nukari.timestamp();
	Assert.assertEquals(updatedstamp, timestamp);
}
	
@AfterMethod
public  void getmethodstatus (ITestResult result) throws IOException {
	getResult(result);
}
	
@AfterClass	
public void teardown() {
	driver.quit();
}

@AfterSuite
public void endreport() {
	endresult();
}
	

}
