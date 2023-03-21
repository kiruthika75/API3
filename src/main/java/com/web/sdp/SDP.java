package com.web.sdp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.pom.HomePage;
import com.pom.SearchResultPage;

public class SDP {
	public WebDriver driver;
	HomePage hp;
	SearchResultPage sp;

	public SDP(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public HomePage getHomePage() {
		if (hp==null) {
			hp = new HomePage(driver);
		}
		return hp;
	}
	
	public SearchResultPage getSearchResultPage() {
		if (sp==null) {
			sp = new SearchResultPage(driver);
		}
		return sp;
	}

}
