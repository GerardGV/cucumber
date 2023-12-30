Feature: Add Random Friend
  Scenario Outline: Add Random Friend

    Given the user is in the main page logged in with its <userEmail> and <userPassword>
    When the user enters in its profile
    And the user selects the first person in its friend suggestions
    And the user sends the friend invitation
    Then the friend invitation is sent correctly

    Examples:
    |userEmail|userPassword|
    |b7bede658dcca4@crankymonkey.info|idafsghbg12345|