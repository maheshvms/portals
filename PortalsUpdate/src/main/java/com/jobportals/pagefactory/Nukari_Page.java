package com.jobportals.pagefactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Nukari_Page extends Baseclass {

	public Nukari_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

@FindBy(xpath="//*[contains(text(),'Login')]")
WebElement  Login;
	
@FindBy(xpath = "//input[contains(@placeholder,'Enter your active Email ID / Username')]")
WebElement  Emailfield;

@FindBy(xpath = "//input[contains(@type,'password')]")
WebElement  Passwordfield;

@FindBy(xpath = "//button[@type='submit'][contains(.,'Login')]")
WebElement  LoginBtn;

@FindBy(xpath="//*[contains(text(),'My Naukri')]")
WebElement  Nukarimenu;

@FindBy(xpath = "//a[contains(@title,'Edit Profile')]")
WebElement  Editprofile;

@FindBy(xpath = "//input[contains(@id,'attachCV')]")
WebElement  UpdateResume;

@FindBy(xpath="//*[contains(text(),'Resume has been successfully uploaded.')]")
WebElement  UpdateSuccess;

@FindBy(xpath="//*[contains(text(),'Home | Mynaukri')]")
WebElement  Hometitle;

@FindBy(xpath = "//span[contains(@class,'updateOn')]")
WebElement  updatedstamp;

public String sitename() {
	String title = driver.getTitle();
	return title;
}

public String validlogin() {
	Login.click();
	Emailfield.sendKeys("vmahesh1415@gmail.com");
	Passwordfield.sendKeys("Silpavaka@1");
	LoginBtn.click();
	Hometitle.isDisplayed();
	String url = driver.getCurrentUrl();
	return url;
}

public String  updateresume() throws Throwable {
	Actions act=new Actions(driver);
	act.moveToElement(Nukarimenu).perform();
	Thread.sleep(1000);
    Editprofile.click();
    Thread.sleep(3000);
    String Resumefile = System.getProperty("user.dir");
    UpdateResume.sendKeys(Resumefile + "/NewResume/MaheshVaka.docx");
    Thread.sleep(3000);
    UpdateSuccess.isDisplayed();
   String stamp = updatedstamp.getText();
   return stamp;
}

public String timestamp() {
	String pattern = "MMM d, yyyy";
	SimpleDateFormat date  = new SimpleDateFormat(pattern);
	String timestamp = "Uploaded on " + date.format(new Date());
	return timestamp;

}


public void  closechildwindows() {
	
	String mainwindow = driver.getWindowHandle();
	Set<String> handle=driver.getWindowHandles();
	for(String handles:handle)
	{
	   if(!mainwindow.equals(handles)){
	      try
	    {
	         String text=driver.switchTo().window(handles).getPageSource();
	         if(text.contains("Genpact"))
	            {
	               System.out.println("Text found");
	               driver.close();
	               break;
	             }
	   }catch(Exception e)
	      {

	      }
	   }
	}
}












}
