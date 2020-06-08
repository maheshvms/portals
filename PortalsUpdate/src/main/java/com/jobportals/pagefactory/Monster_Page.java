package com.jobportals.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Monster_Page extends Baseclass {
	
	public Monster_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//span[contains(.,'Jobseeker Login')]")
	WebElement Login;
	
	@FindBy(xpath = "//input[@class='input']")
	WebElement Emailfield;
	
	@FindBy(xpath = "//input[@class='input password-input']")
	WebElement Passwordfield;
	
	@FindBy(xpath = "//input[@id='signInbtn']")
	WebElement LoginBtn;
	
	@FindBy(xpath = "//a[contains(.,'Update Profile')]")
	WebElement Monsterupdate;
	
	@FindBy(xpath = "//i[@class='mqfi-upload']")
	WebElement uploadbtn;
	
	@FindBy(xpath = "//input[@name='resume']")
	WebElement UpdateResume;
	
	@FindBy(xpath = "//*[contains(text(),'My Dashboard | Monster India')]")
	WebElement Hometitle;
	
	@FindBy(xpath = "(//div[@id='modalDescription']//div[@class='col-md-12 text-right']/button[contains(text(),'Save')])[3]")
	WebElement Save;
	
	@FindBy(xpath = "//*[contains(text(),'Thanks for uploading your resume. You are now one step closer in your job search.')]")
	WebElement uploadsuccess;
	
	@FindBy(xpath = "//*[@class='color-grey medium']")
	WebElement uploadreturn;
	

	public String sitename() {
		String title = driver.getTitle();
		return title;
	}

	public String validlogin() {
		Login.click();
		Emailfield.sendKeys("vmahesh1415@gmail.com");
		Passwordfield.sendKeys("silpavaka");
		LoginBtn.click();
		Hometitle.isDisplayed();
		String url = driver.getCurrentUrl();
		return url;
	}

	public String  updateresume() throws Throwable {
		Monsterupdate.click();
	    Thread.sleep(3000);
	    uploadbtn.click();
	    String Resumefile = System.getProperty("user.dir");
	    UpdateResume.sendKeys(Resumefile + "/NewResume/MaheshVaka.docx");
	    Thread.sleep(3000);
	    Save.click();
	    uploadsuccess.isDisplayed();  
	   String resumename = uploadreturn.getText();
	   return resumename;
	}
	
	
	

}
