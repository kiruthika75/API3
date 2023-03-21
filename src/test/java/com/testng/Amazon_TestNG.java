package com.testng;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Spt.Febbatch.SPT_Oct_Batch.XLSXReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon_TestNG {
	public static WebDriver driver;
	static String searchIn;
	static String dropInput;
	@BeforeSuite
	public static void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		System.out.println("Before Suite Executed");
	}
	@BeforeTest
	public void browser_Maximize() {
		driver.manage().window().maximize();
		System.out.println("Before test Executed");
	}
	@BeforeClass
	public void user_launch_browser() {
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
	@Test(priority=0)
	public void select_from_dropdown() throws Exception {
		dropInput = XLSXReader.singleData("AMAZON", 11, 0);
		WebElement dropDown = driver.findElement(By.id("searchDropdownBox"));
		Select s = new Select(dropDown);
		List<WebElement> options = s.getOptions();
		for (int i = 0; i < options.size(); i++) {
			String text = options.get(i).getText();
			if (dropInput.equalsIgnoreCase(text)) {
				s.selectByVisibleText(text);
			}
		}
		System.out.println("test 1 done");
	}
	@Test(priority=1)
	public void send_the_value() throws Exception {
		searchIn = XLSXReader.singleData("AMAZON", 11, 1);
		WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
//		searchIn = searchInput;
		search.sendKeys(searchIn);
		Thread.sleep(2000);
		System.out.println("test 2 done");
	}
	@Test(priority=2)
	public void find_the_exact_searchvalue_in_the_suggestion_click() {
		List<WebElement> allElements = driver.findElements(
				By.xpath("//div[@class='autocomplete-results-container']/child::div/child::div/child::div"));

		for (int i = 1; i <= allElements.size(); i++) {
			WebElement eachElement = driver.findElement(By.xpath(
					"//div[@class='autocomplete-results-container']/child::div[" + i + "]/child::div/child::div"));
			String text = eachElement.getText();
			if (searchIn.equalsIgnoreCase(text)) {
				eachElement.click();
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
		driver.manage().deleteAllCookies();
		System.out.println("After Test Executed");
	}
	@AfterSuite
	public static void tearDown() {
		System.out.println("After Suite Executed");
		driver.close();
	}
}
