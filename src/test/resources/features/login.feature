@login
Feature: Search
  As a user I should be able to see search results

  Background: Goto Google search page
    * I navigate to "https://www.google.co.in/"

  Scenario: I search for UBS
    * Search "UBS"
    * Search result page is displayed

  Scenario: I search for UBS Pune
    * Search "UBS Pune"
    * Search result page is not displayed
