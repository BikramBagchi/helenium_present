package com.orangehrm.test;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.orange.base.TestBase;

import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.PropertiesFileDataHandeling;
import com.orangehrm.utils.SeleniumUtils;


public class LoginTest extends TestBase 
{
	
	
	LoginPage lp;
	TestBase tb;
	SeleniumUtils su;
	PropertiesFileDataHandeling pdh;
	
	
	
	@BeforeMethod(alwaysRun = true)
	public void open_browser() throws Throwable
	{
		tb = new TestBase();
		tb.openBrowser();
		
	}
	
	@Test(priority = 1 , enabled = true ,  groups = "smoke" , description = "This one will check wheather the application can login or not with correct user name and password" )
	public void login() throws Throwable
	{
		lp = new LoginPage();
		lp.user_name_input();
		lp.password_input();
		lp.click_submit_button();
		su = new SeleniumUtils();
		pdh = new PropertiesFileDataHandeling();
		Assert.assertEquals(su.Title_name(), pdh.GetPropertyValue("app_title"));
		
	}
	
	@Test(priority = 2 , enabled = false  , groups = "regression" , dependsOnMethods = "login" ,  description = "This one will check the error message at the time of wrong username" )
	public void login_invalid_text() throws Throwable
	{
		lp = new LoginPage();
		lp.wrong_username();
		lp.password_input();
		lp.click_submit_button();
		pdh = new PropertiesFileDataHandeling();
		Assert.assertEquals(lp.wrmsg(), pdh.GetPropertyValue("user_error_msg"));
		
	}
	
	@Test(priority = 3 , enabled = false  , groups = "regression" , dependsOnMethods = "login" ,  description = "This one will check the error message at the time of wrong password" )
	public void invalid_pass() throws Throwable
	{
		lp = new LoginPage();
		lp.user_name_input();
		lp.password_input();
		lp.click_submit_button();
		pdh = new PropertiesFileDataHandeling();
		Assert.assertEquals(lp.wrmsg(),pdh.GetPropertyValue("user_error_msg") );	
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeBrowaser()
	{
		tb = new TestBase();
		tb.Close_Browser();
	}

}
