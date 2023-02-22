package com.Actitime.Testscripts;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Actitime.GenericLibrary.BaseClass;
import com.Actitime.GenericLibrary.File_Library;
import com.Actitime.pom.Homepage;
import com.Actitime.pom.TaskPage;

public class A1_CreateCustomer extends BaseClass //whenever we want to use base class in scripts in we just extends this
//is the oops concepts we are use in selenium
{
	File_Library flib=new File_Library();//create object globally
	
	@Test
	public void createcustomer() throws EncryptedDocumentException, IOException, InterruptedException
	{
		Homepage hp=new Homepage(driver);
		hp.getTasklnk().click();

		TaskPage tp=new TaskPage(driver);
		String custname = flib.readDataFromExcelFile("Sheet1", 3, 1);
		String description = flib.readDataFromExcelFile("Sheet1", 1, 1);
		tp.completetask(custname, description);
		String expected = custname;
		System.out.println(expected);
		String actualdata = driver.findElement(By.xpath("//div[@title='"+custname+"']")).getText();
		SoftAssert s=new SoftAssert();
		s.assertEquals(expected, actualdata);
		Reporter.log("assertion pass",true);
		s.assertAll();
	}
}
