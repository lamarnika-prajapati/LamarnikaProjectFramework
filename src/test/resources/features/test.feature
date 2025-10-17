Feature: google search

  @googleSearch
  Scenario Outline: test google search functionality
    Given user navigates to "https://www.google.co.in/"
    When input some "<Search keywords>" keywords
    Then click on search button

    Examples:
      | Search keywords      |
      | Let's get started    |
#      | We started perfectly |

