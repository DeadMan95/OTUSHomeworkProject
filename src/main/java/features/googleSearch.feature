Feature: I want to test Google.com

  Scenario: I want to search "google"
    Given I go to home page
    When I type "google" and submit
    Then I see page with "google - Поиск в Google" title
