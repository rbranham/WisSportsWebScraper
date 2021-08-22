package com.HBSS.pages;

import java.util.ArrayList;
import java.util.List;

import com.HBSS.models.TeamConferenceSeasonQuickStats;
import com.HBSS.pages.PageSuper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ConferencePage extends PageSuper {
	
	final public static String[] yearString = new String[] {
		"2019-20",
		"2018-19",
		"2017-18"
	};
	
	//Constants
	final private String BASE_URL = "https://www.wissports.net"; //This value could be refactored into wissports superclass
	final private String CONFERENCE_PAGE_POINT = "/page/show";
	final private String FULL_URL = BASE_URL + CONFERENCE_PAGE_POINT; 
	
	//Useful By locators for finding important WebElements
	final private By seasonDropDown = By.id("megaDropDown-season");
	final private By seasonCallout = By.id("megaDropDown-season-callout");
	final private By statTable = By.className("statTable");
	
	
	public ConferencePage(WebDriver driver) {
		super(driver);
	}
	
	//Conference value is actualy for each season for each conference. But only need to get onto one page and then can naviagate to each season through clicks on dropdown
	public void goTo(String conferenceValue) {
		driver.get(FULL_URL + "/" + conferenceValue);    
	}
	
	public ArrayList<ArrayList<TeamConferenceSeasonQuickStats>>  getStatsForSeasonList(String[] seasonStrings, String currentPageSeason){
		
		ArrayList<ArrayList<TeamConferenceSeasonQuickStats>> masterList = new ArrayList<ArrayList<TeamConferenceSeasonQuickStats>>();
		
		//Should check if current page matches one in the string array and delete it if so
		masterList.add(readStatsTable(currentPageSeason));
		
		//Go through each season and get stats
		for(String s: seasonStrings) {
			
			changeSeasonTo(s);
			
			masterList.add(
					readStatsTable(s)
					);
			
		}
		
		return masterList;
		
	}
	
	//Change Season
	public void changeSeasonTo(String seasonText) {
		
		openSeasonCallout();
		
		WebElement dropdown = driver.findElement(seasonCallout).findElement(By.xpath(".//div[2]/select"));

		String s = "//optgroup[@label='"
				+ seasonText
				+ "']/option";
		
		dropdown.findElement(By.xpath(s))
				.click(); //Should open page
		
	}
	
	//Open season dropdown
	private void openSeasonCallout() {
		driver.findElement(seasonDropDown).click();
	}
	
	//Read Table
	public ArrayList<TeamConferenceSeasonQuickStats> readStatsTable(String season){
		ArrayList<TeamConferenceSeasonQuickStats> tableContents = new ArrayList<TeamConferenceSeasonQuickStats>();
		
		List<WebElement> items = driver.findElement(statTable)
				.findElements(By.xpath(".//tbody/tr"));
		
		for(WebElement i : items) {
			
			List<WebElement> e = i.findElements(By.xpath(".//td"));

			tableContents.add(new TeamConferenceSeasonQuickStats(
						e.get(0).getText(), // Name
						Integer.parseInt(e.get(2).getText()), 
						Integer.parseInt(e.get(3).getText()),
						e.get(4).getText(),
						e.get(5).getText(),
						season
					));
			
		}
		
		return tableContents;
	}
	
}
