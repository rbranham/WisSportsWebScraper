package com.HBSS.main;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.HBSS.models.TeamConferenceSeasonQuickStats;
import com.HBSS.pages.*;

public class main {

	private final static String DRIVER_PATH = "/Users/Roger/Documents/Tools/chromedriver.exe";
	private final static String INDIANHEAD_STRING = "5798944-indianhead";
	
	public static void main(String[] args) {
		
		// Optional. If not specified, WebDriver searches the PATH for chromedriver.       
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH );  //C:\Users\Roger\Documents\Tools 
		WebDriver driver = new ChromeDriver(); 	
		
		//TODO: Add fluent wait to driver? 
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		ConferencePage indianHead = new ConferencePage(driver);
		indianHead.goTo(INDIANHEAD_STRING);
				
		customSleep();
		
		//indianHead.changeSeasonTo("2019-20");
		ArrayList<ArrayList<TeamConferenceSeasonQuickStats>> temp = indianHead.getStatsForSeasonList(ConferencePage.yearString, "2020-21");
		
		customSleep();
		
		for(ArrayList<TeamConferenceSeasonQuickStats> seasonList : temp) {
			
			for(TeamConferenceSeasonQuickStats q : seasonList) {
				System.out.println(q);
			}
			
		}
		
//		ArrayList<TeamSeasonQuickStats> temp = indianHead.readStatsTable();
//		
//		for(TeamSeasonQuickStats q : temp) {
//			System.out.println(q);
//		}
		
	}
	
	private static void customSleep() {
		try {
			System.out.println("Sleeping");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  // Let the user actually see something! 
	}

}
