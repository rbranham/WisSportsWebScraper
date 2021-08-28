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
The code is written in Java, using Selenium to provide an API for interacting with the web (the website does not provide and api). The webscrapping implementation follows 
the Page Object Model pattern, where there is a class for each page, with constants for features and methods to get data. This way if the web page is changed in the future
maintenance will be easier. 

In addition to the web scrapping, a data access module has been included to write the data to a database. Since the information on the site does not change often, to reduce 
load on the site best practice would be to read the data and write to an sql server. Then, any front end to display the data can be read from the database. Only at the conclusion
of a sports season would the webscrapper be run to collect new standings. 

