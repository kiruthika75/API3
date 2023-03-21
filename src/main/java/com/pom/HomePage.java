package com.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
public WebDriver driver;
	public HomePage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
	}

	@FindBy(id = "searchDropdownBox")
	private WebElement dropDown;
	
	@FindBy(id = "twotabsearchtextbox")
	private WebElement search;
	
	@FindBy(xpath = "//div[@class='autocomplete-results-container']/child::div/child::div/child::div")
	private List<WebElement> allElements;
	
	@FindBy(xpath = "//div[@class='autocomplete-results-container']/child::div[\" + i + \"]/child::div/child::div")
	private WebElement eachElement;
	
	
	public WebElement getDropDown() {
		return dropDown;
	}

	public WebElement getSearch() {
		return search;
	}

	public List<WebElement> getAllElements() {
		return allElements;
	}

	public WebElement getEachElement() {
		return eachElement;
	}
	
	
}
