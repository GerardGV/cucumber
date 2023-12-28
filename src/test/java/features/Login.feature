Feature: Login
  Scenario Outline: Login

    Given the user is in the index page to login
    When the user clicks Login
    And the user enters <emailLogin> in the email bar
    And the user enters in the password bar to log in the <passwordLogin>
    And the user clicks connect
    Then the user is logged

    Examples:
      |emailLogin|passwordLogin|
      |b7bede658dcca4@crankymonkey.info|idafsghbg12345|