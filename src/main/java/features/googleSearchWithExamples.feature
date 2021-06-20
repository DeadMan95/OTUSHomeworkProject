Feature: I want to test Google.com

  Scenario Outline: I want to search "google"
    Given I go to home page
    When I type "<searchString>" and submit
    Then I see page with "<title>" title

    Examples:
      | searchString | title                   |
      | google       | google - Поиск в Google |
      | яндекс       | яндекс - Поиск в Google |
      | otus         | otus - Поиск в Google   |