package com.HBSS.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.HBSS.data.DAOInterface;
import com.HBSS.data.DAOUtil;
import com.HBSS.data.MySQLDAO;
import com.HBSS.models.*;
import com.HBSS.pages.*;

public class main {

	private final static String DRIVER_PATH = "/Users/Roger/Documents/Tools/chromedriver.exe";
	private final static String INDIANHEAD_STRING = "5798944-indianhead";
	
	public static void main(String[] args) {
		
		//DAO manual testing
		DAOInterface db = MySQLDAO.getInstance();
		Season s = new Season(); 
		s.setSeasonString("2019-20");
		
		Conference c = new Conference(); 
		c.setConferenceName("Indianhead2");
		
		
		try {
//			//db.addSeason(s);
			//db.deleteSeason(2);
//			ArrayList<Season> seasons = db.getAllSeasons();
//			
//			for(Season se : seasons) {
//				System.out.println(se);
//			}
			
//			System.out.println(db.getSeason(1)); 
			
			//Conference Manual prototype testing
			//db.addConference(c);
			//System.out.println(db.getConference(1));
			
			//db.deleteConference(2);
			
			//ArrayList<Conference> confs = db.getAllConferences();
			
			//confs.stream().forEach(System.out :: println);
			db.getAllConferences().stream().forEach(System.out :: println);
			
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Web Scrapping manual testing
//		mainCode(); 
//		
//		ArrayList<String> c = new ArrayList<String>();
//		
//		c.add("email");
//		c.add("password");
//		c.add("firstname");
//		
//		
//		System.out.println(
//				DAOUtil.generateInsert("User", c)
//				);
		
		
		//DAO Util manual testing
//		ArrayList<String> c = new ArrayList<String>();
//		
//		c.add("email");
//		
//		
//		System.out.println(
//				DAOUtil.generateInsert("User", c)
//				);
		
		
	}
	
	private static void mainCode() {
		
		// Optional. If not specified, WebDriver searches the PATH for chromedriver.       
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH );  //C:\Users\Roger\Documents\Tools 
		WebDriver driver = new ChromeDriver(); 	
		
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
