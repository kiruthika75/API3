package com.stepdefintion;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import com.Runner.Runner;
import com.basefiles.BaseClass;
import com.property.ConfigurationHelper;
import com.web.sdp.SDP;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseClass{

	public static WebDriver driver = Runner.driver;
	SDP sdp = new SDP(driver);
	static String searchIn;
//	static String dropInput;

	
	
	@Given("user Launch Browser")
	public void user_launch_browser() throws IOException {
		launchUrl(ConfigurationHelper.getInstance().getUrl());
		maximize();
		implicitlyWait(10);
	}
	@When("select {string} from Dropdown")
	public void select_from_dropdown(String dropInput) throws Exception {
		WebElement dropDown = sdp.getHomePage().getDropDown();
		List<WebElement> options =dropDownGetOptions(dropDown, "all");
		for (int i = 0; i < options.size(); i++) {
			String text = getText(options.get(i));
			if (dropInput.equalsIgnoreCase(text)) {
				dropDownSelect(dropDown, "text", text);
			}
		}
	}
	@Then("Send The Value {string}")
	public void send_the_value(String searchInput) throws Exception {
		WebElement search =sdp.getHomePage().getSearch();
		searchIn = searchInput;
		userInput(search, searchInput);
		sleep(2000);
	}
	@And("find The Exact Searchvalue In The Suggestion Click")
	public void find_the_exact_searchvalue_in_the_suggestion_click() {
		List<WebElement> allElements = sdp.getHomePage().getAllElements();

		for (int i = 1; i <= allElements.size(); i++) {
			WebElement eachElement = sdp.getHomePage().getEachElement();
			String text = getText(eachElement);
			if (searchIn.equalsIgnoreCase(text)) {
				clickOnElementJS(eachElement);
				break;
			}else continue;
		}
//			for (WebElement eachElement : allElements) {
//				String text = getText(eachElement);
//				if (searchIn.equalsIgnoreCase(text)) {
//					clickOnElement(eachElement);
//					break;
//			}
//		}
	}
}
