# WisSportsWebScraper

The goal of this project is to provide webscraping tools for this website: https://www.wissports.net/, which holds stats for highschool sports teams in Wisconsin.
The main focus is on being able to collect the conference standings for a given basketball conference. The end goal would then be to graph conference standings over time, 
to display the best and worst performing teams in a conference over time. 

## Inspiration
This project was inspired by some manual stat collecting I did of my Alma Mater's conference in 2020 as a curiosity. Here is the google sheets file for that: 
https://docs.google.com/spreadsheets/d/1kduOTXGNvMqNbz4Vgv9emM5LurbsEZrI5Sssc-qsZoU/edit?usp=sharing

Collecting the stats manually is not difficult, but is time consuming and tedious. Since the data is all online I wanted to automate the task so any conference could 
be viewed quickly. 

## Implementation Details
The code is written in Java, using Selenium to provide an API for interacting with the web (the website does not provide any api). The webscrapping implementation follows 
the Page Object Model pattern, where there is a class for each page, with constants for features and methods to get data. This way if the web page is changed in the future
maintenance will be easier. 

In addition to the web scrapping, a data access module has been included to write the data to a database. Since the information on the site does not change often, to reduce 
load on the site best practice would be to read the data and write to an sql server. Then, any front end to display the data can be read from the database. Only at the conclusion
of a sports season would the webscrapper be run to collect new standings. 

A Spring Application has been included with an exposed REST API for the data. It makes use of the same data package used to write the data to the database. Look into the How to Use section for a quick overview of setting everything up. 

## How to Use

There are currently two parts to this project: The Webscraper and the Spring REST server. Since the scraped data is not changed often, I'm leaving it to be run manually in the com.HBSS.main.main.java file. Set up a database(column and table names are stored as constants at the top of MySQLDAO.java), and then change the code by uncommenting the scrape code and change the conference string to what ever conference you want. You'll also need to add a season list to the database before scraping. 

After scraping a conference, or seperately, as it doesn't really matter, you can run the Spring Application in the file: com.HBSS.main.ConferenceStatsApplication.java . This will expose endpoints to access the data you scraped into the database. This could be hosted forever in the cloud, but for me I just store it on a local MySQL server on my machine. 

### Endpoints

Names are fairly self explanatory. Try them out in postman, or just look at the underlying data models to make sense of what they return. 

/conferences
/conference/{confId}
/teams
/teams/{teamsId}
/seasons

