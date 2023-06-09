package com.testng;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.basefiles.BaseClass;
import com.property.ConfigurationHelper;
import com.web.sdp.SDP;

public class Amazon_TestNGParameter4BaseCO_WDM extends BaseClass {
	String searchIn;
	static String dropInput;
	static SDP sdp;

	@BeforeSuite
	public static void setUp() throws IOException {
		browserLaunch(ConfigurationHelper.getInstance().getBrowser());
		sdp = new SDP(driver);
		implicitlyWait(10);
		System.out.println("Before Suite Executed");
	}

	@BeforeTest
	public void browser_Maximize() {
		maximize();
		System.out.println("Before test Executed");
	}

	@BeforeClass
	public void user_launch_browser() throws IOException {
		launchUrl(ConfigurationHelper.getInstance().getUrl());
		System.out.println("Before Class Executed");
	}

	@BeforeMethod
	public void beforeMethod_Statement() {
		System.out.println("before MEthod");
	}

	@AfterMethod
	public void afterMethod_Statement() {
		System.out.println("after MEthod");
	}

	@Parameters("dropDownValue")
	@Test(priority = 0)
	public void select_from_dropdown(String dropInput) throws Exception {
//		dropInput = XLSXReader.singleData("AMAZON", 11, 0);
		WebElement dropDown = sdp.getHomePage().getDropDown();
		List<WebElement> options = dropDownGetOptions(dropDown, "all");
		for (int i = 0; i < options.size(); i++) {
			String text = getText(options.get(i));
			if (dropInput.equalsIgnoreCase(text)) {
				dropDownSelect(dropDown, "text", text);
			}
		}
		System.out.println("test 1 done");
	}

	@Parameters("searchValue")
	@Test(priority = 1)
	public void send_the_value(String searchIn) throws Exception {
		WebElement search = sdp.getHomePage().getSearch();
		this.searchIn = searchIn;
		userInput(search, searchIn);
		sleep(2000);
		System.out.println("test 2 done");
	}

	@Test(priority = 2)
	public void find_the_exact_searchvalue_in_the_suggestion_click() {
		List<WebElement> allElements = sdp.getHomePage().getAllElements();

		for (int i = 1; i <= allElements.size(); i++) {
			WebElement eachElement = sdp.getHomePage().getEachElement();
			String text = getText(eachElement);
			if (searchIn.equalsIgnoreCase(text)) {
				clickOnElement(eachElement);
				break;
			}
		}
		System.out.println("test 3 done");
	}

	@AfterClass
	public void afterClassStatement() {
		System.out.println("after class executed");
	}

	@AfterTest
	public void cookie_delete() {
		deleteCookies();
		System.out.println("After Test Executed");
	}

	@AfterSuite
	public static void tearDown() {
		System.out.println("After Suite Executed");
		quit();
	}
}
