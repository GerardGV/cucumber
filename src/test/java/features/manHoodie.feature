Feature:ManHoodie
  Scenario: Show Man Hoodie

    Given the user is in the index page
    When the user clicks man options
    And the user enters hoodie in the search bar
    And the user clicks the search button
    Then the hoodie list appears