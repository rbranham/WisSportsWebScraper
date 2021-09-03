package com.HBSS.pages;

import org.openqa.selenium.WebDriver;

/**
 * Super class for every page, 
 * defines constructor and driver requirement for every page. 
 * 
 * @author Roger Branham
 *
 */
public class PageSuper {

	protected WebDriver driver;
	
	public PageSuper(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebDriver getDriver() {
		return driver; 
	}
}
