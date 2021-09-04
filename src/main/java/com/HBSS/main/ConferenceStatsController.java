package com.HBSS.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConferenceStatsController {

	@GetMapping("/test") 
	public String test() {
		
		return "Hello World";
	}
	
}
