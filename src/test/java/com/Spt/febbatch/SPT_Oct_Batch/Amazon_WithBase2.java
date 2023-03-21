
package com.Spt.febbatch.SPT_Oct_Batch;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.Spt.Febbatch.SPT_Oct_Batch.XLSXReader;
import com.basefiles.BaseClass;
import com.property.ConfigurationHelper;
import com.web.sdp.SDP;

public class Amazon_WithBase2 extends BaseClass{
public static SDP sdp;
	public static void main(String[] args) throws InterruptedException, Exception {
		browserLaunch(ConfigurationHelper.getInstance().getBrowser());
		sdp = new SDP(driver);
		maximize();
		implicitlyWait(10);
		launchUrl(ConfigurationHelper.getInstance().getUrl());

		String dropInput = XLSXReader.singleData(ConfigurationHelper.getInstance().getSheet(), 11, 0);
		String searchInput = XLSXReader.singleData(ConfigurationHelper.getInstance().getSheet(), 11, 1);
		WebElement dropDown = sdp.getHomePage().getDropDown();
		
		List<WebElement> options = dropDownGetOptions(dropDown, "all");
		for (int i = 0; i < options.size(); i++) {
			String text = getText(options.get(i));
			if (dropInput.equalsIgnoreCase(text)) {
				dropDownSelect(dropDown, "text", text);
			}
		}	

		WebElement search = sdp.getHomePage().getSearch();
		userInput(search, searchInput);
		sleep(2000);

		List<WebElement> allElements = sdp.getHomePage().getAllElements();

		for (int i = 1; i <= allElements.size(); i++) {
			WebElement eachElement = sdp.getHomePage().getEachElement();
			String text = getText(eachElement);
			if (searchInput.equalsIgnoreCase(text)) {
				clickOnElement(eachElement);
				break;
			} else {
				continue;
			}
		}
		deleteCookies();
		quit();
	}

}
