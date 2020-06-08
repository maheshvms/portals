package com.jobportals.testscripts;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.jobportals.pagefactory.Baseclass;
import com.jobportals.pagefactory.Monster_Page;

public class Monster_Test extends Baseclass {
	
	private Monster_Page monster;
	
	public Monster_Test() {
		super();
	}
	
	@BeforeClass
	public void setup() {
		Initialization();
		geturl(prop.getProperty("url2"));
		monster =new Monster_Page(driver);
		
	}
	
	@Test(priority=1, enabled = true)
	public void monstersitevalidation() {
		test = extent.createTest("monstersitevalidation");
		String sitename = monster.sitename();
		Assert.assertEquals(sitename, "Job Vacancy - Latest Job Openings - Job Search Online - Monster India");
	}

	@Test (priority=2, enabled = true)
	public void monstervalidlogin() {
		test = extent.createTest("validlogin");
		String siteurl = monster.validlogin();
		if (siteurl.contains("dashboard"))
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
	}

	@Test(priority=3, enabled = true)
	public void monsterupdateresume() throws Throwable {
		test = extent.createTest("monsterupdateresume");
		String resume = monster.updateresume();
		Assert.assertEquals(resume, "MaheshVaka.docx");
	}
	
		
	@AfterClass	
	public void teardown() {
		driver.quit();
	}
	
	
	

}
