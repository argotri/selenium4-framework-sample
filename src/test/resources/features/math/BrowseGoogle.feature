Feature: Coba Google

  Scenario: user search apple in google
    Given user is on google.com
    When user search "apple"
    Then user should see "apple" in the search result