Feature: feature to test detail of book page

  Scenario: Verify data in detail book has same value with data in listing page
    Given User go to demoqa.com/books
    When User in the Book Store page
    And User search book Git Pocket Guide
    And User click book Git Pocket Guide
    Then User see Git Pocket Guide
