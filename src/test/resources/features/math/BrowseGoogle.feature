Feature: Coba Google

  Scenario: user search apple in google
    Given user is on google.com
    When user search "apple"
    Then user should see "apple" in the search result

  Scenario: user search kiwi in google
    Given user is on google.com
    When user search "kiwi"
    Then user should see "kiwi" in the search result