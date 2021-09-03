package com.HBSS.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Data class to hold the record stats for a team in a given year. 
 * 
 * @author Roger Branham
 *
 */
@Getter @Setter @ToString
public class TeamConferenceSeasonQuickStats {
	
	private int seasonId; 
	private int conferenceId; 
	private int teamId; 
	private int wins;
	private int losses; 
	private String overall;

}
