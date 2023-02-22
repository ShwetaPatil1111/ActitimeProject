//we are create once used multiple times
//for installed Testng in project rigt click on project and then build path-->add libraries

package com.Actitime.GenericLibrary;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.Actitime.pom.Homepage;
import com.Actitime.pom.LoginPage;
//Generic classes
public class BaseClass {
	File_Library f=new File_Library();	
	public static WebDriver driver; //declare it globally to use in all variables
	@BeforeSuite
	public void databaseconnection()  //Generic methods
	{
		Reporter.log("Database connected",true);
	}
	
	@AfterSuite
	public void databasedisconnection()
	{
		Reporter.log("Database disconnected",true);
	}
	@BeforeClass
	public void launchBrowser() throws IOException
	{
		driver=new ChromeDriver(); //if we are not remove that webdriver it will give exception null pointer and
		//open browser 2 times
		driver.manage().window().maximize();
		String URL = f.reaDataFromPropertyFile("url");
		driver.get(URL);
		Reporter.log("Browser launched succesfully",true);
	}
	@AfterClass
	public void closeBrowser()
	{
		driver.close();
		Reporter.log("Browser closed successfully",true);
	}
	@BeforeMethod
	public void loginToActitime() throws IOException
	{
		String UN = f.reaDataFromPropertyFile("username");
		String PW = f.reaDataFromPropertyFile("password");
		LoginPage lp=new LoginPage(driver); //we are use pom class here
		lp.login(UN, PW);
	//	driver.findElement(By.id("username")).sendKeys(UN); 
	//	driver.findElement(By.name("pwd")).sendKeys(PW);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//div[.='Login ']")).click();
		Reporter.log("Login successfully",true);
	}
	@AfterMethod
	public void logoutFromActitime() throws InterruptedException
	{
	//	Thread.sleep(2000);
		Homepage hp=new Homepage(driver);
		Thread.sleep(2000);
		hp.getLgoutlnk().click();
	//	driver.findElement(By.id("logoutLink")).click();
		Reporter.log("Logout successfully",true);
	}
}
