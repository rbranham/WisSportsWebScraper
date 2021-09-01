package com.HBSS.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * POJO class for holding conference data. Attributes are: 
 * 
 * <ul>
 * <li> Id - Is the unique conference id as given by the database. Leave null when adding new conference to database
 * <li> conferenceName - Name of the conference
 * </ul>
 * 
 * @author Roger Branham
 */
@Getter @Setter @ToString
public class Conference {
	
	private Integer id; 
	private String conferenceName;
	
}
