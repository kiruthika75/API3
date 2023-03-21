package com.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {
	public WebDriver driver;
	public SearchResultPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
	}
}
