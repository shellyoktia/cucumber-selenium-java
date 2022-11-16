Feature: feature to test searching qa engineer in book store page

  Scenario: Verify when user search qa engineer then user will see no rows found
    Given User go to demoqa.com
    When User in Book Store page
    And User search book qa engineer
    Then User see No rows found
