package com.softwaretestinghelp.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class setupSearchCars {

	
		@FindBy(css = "#postcode")
		private WebElement setpostcodeInput;
		
		public void userpostcodeInput(String postcode) 
			{
			setpostcodeInput.clear();
			setpostcodeInput.sendKeys(postcode);
			}


		}
	
	

