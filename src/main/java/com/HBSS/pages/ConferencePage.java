package com.HBSS.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ConferencePage extends PageSuper {

	
	//Constants
	final private String BASE_URL = "https://www.wissports.net"; //This value could be refactored into wissports superclass
	final private String CONFERENCE_PAGE_POINT = "/page/show";
	final private String FULL_URL = BASE_URL + CONFERENCE_PAGE_POINT; 
	
	//Useful By locators for finding WebElements
	final private By seasonDropDown = By.id("megaDropDown-season");
	final private By seasonCallout = By.id("megaDropDown-season-callout");
	final private By statTable = By.className("statTable");
	
	
	public ConferencePage(WebDriver driver) {
		super(driver);
	}
	
	public void goTo(String conferenceValue) {
		driver.get(FULL_URL + "/" + conferenceValue);    
	}
	
	//Get current season TODO: Not working
	private String getSelectedSeasonString() {
		String s = ""; //Stubbed
		// Xpath: //*[@id="megaDropDown-season"]/span[2]
		
		return s;
	}
	
	//Change Season
	public void changeSeasonTo(String seasonText) {
		openSeasonCallout();
		//Select seasonDropdown = new Select(driver.findElement(seasonCallout).findElement(By.xpath(".//div[1]/select")));
		WebElement dropdown = driver.findElement(seasonCallout).findElement(By.xpath(".//div[2]/select"));
		//String p = "//optgroup[@label=";
		dropdown.findElement(By.xpath("//optgroup[@label='2019-20']/option"))
				.click(); //Should open page
		
		
		//*[@id="megaDropDown-season-callout"]/div[2]/select
		//*[@id="megaDropDown-season-callout"]/div[1]
		//*[@id="megaDropDown-season-callout"]/div[2]/select/optgroup[1]
	}
	
	//Open season dropdown
	private void openSeasonCallout() {
		driver.findElement(seasonDropDown).click();
	}
	
	
	//Read Table
	public ArrayList<TeamSeasonQuickStats> readStatsTable(){
		ArrayList<TeamSeasonQuickStats> tableContents = new ArrayList<TeamSeasonQuickStats>();
		
		List<WebElement> items = driver.findElement(statTable)
				.findElements(By.xpath(".//tbody/tr"));
		
		for(WebElement i : items) {
			
			List<WebElement> e = i.findElements(By.xpath(".//td"));

			tableContents.add(new TeamSeasonQuickStats(
						e.get(0).getText(), // Name
						Integer.parseInt(e.get(2).getText()), 
						Integer.parseInt(e.get(3).getText()),
						e.get(4).getText(),
						e.get(5).getText()
					));
			
		}
		
		
		return tableContents;
	}
	
	
	
}
