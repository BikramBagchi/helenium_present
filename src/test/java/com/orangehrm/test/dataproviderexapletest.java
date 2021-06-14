package com.orangehrm.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orange.base.TestBase;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.SystemUserPage;
import com.orangehrm.utils.fetchingvaluesfromxlxsfile;

public class dataproviderexapletest
{

	HomePage hp;
	TestBase tb;
	LoginPage lp;
	SystemUserPage sup;
	fetchingvaluesfromxlxsfile excelvalue;
//	Object[][] datatest;
	
	@BeforeMethod
	public void open_browser() throws Throwable
	{
		tb = new TestBase();
		tb.openBrowser();
	}	
	@DataProvider
	public Object[][] testvalue()
	{
		return new Object[][] { { "testuser_1", "Test1213" , "Test1213" }, { "testuser_2", "Test121" , "Test121" }, { "testuser_2", "Test4123", "Test4123"} };
		
	}
	
	@DataProvider
	public Object[][] valuetestco() throws Throwable
	{
		excelvalue = new fetchingvaluesfromxlxsfile();
		Object[][] rambo = 	excelvalue.testsheetdatatoexcel("test");
		
		return rambo;
		
	}
	
	@Test(priority = 1, enabled = true , dataProvider = "valuetestco" )
	public void totest(String empname, String pass1, String pass2)
	{
		lp = new LoginPage();
		try
		{
			lp.user_name_input();
			lp.password_input();
		}
		catch(Throwable e)
		{
			
		}
	
		lp.click_submit_button();
		hp = new HomePage();
		sup =hp.ClickOnUser();
		sup.createuserone(empname, pass1, pass2);
	}
	
	@AfterMethod
	public void quite_browser()
	{
		tb = new TestBase();
		tb.Close_Browser();
	}
}
