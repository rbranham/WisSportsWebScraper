package com.HBSS.main;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.HBSS.data.DAOInterface;
import com.HBSS.data.MySQLDAO;
import com.HBSS.models.*;

/**
 * Controller for conference stats endpoints. 
 * Should only expose get commands, adding/editing only should happen internally
 * @author Roger Branham
 *
 */
@RestController
public class ConferenceStatsController {

	
	/**
	 * End point to return all stats for a conference id. 
	 * Spring will auto convert to json using Jackson
	 * @param confId
	 * @return List of conference quick stats for a conference
	 */
	@GetMapping("/conferences/{confId}")
	public ArrayList<TeamConferenceSeasonQuickStats> getConferenceStats(@PathVariable Integer confId) {
		
		try {
			
			DAOInterface db = MySQLDAO.getInstance();			
			return db.getAllForConference(confId);
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * End Point to return all conferences in database
	 * Spring will auto convert to json using Jackson
	 * @return
	 */
	@GetMapping("/conferences")
	public ArrayList<Conference> getConferences() {
		
		try {
			DAOInterface db = MySQLDAO.getInstance();
			return db.getAllConferences();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
