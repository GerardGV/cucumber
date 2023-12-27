Feature: Register
  Scenario Outline: Register

    Given the user is in the index page to register
    When the user clicks Register
    And the user enters <username> in the username bar
    And the user enters <name> the name button
    And the user clicks next 1
    And the user enters temporal <email> in the email bar
    And the user enters the <password>
    And the user clicks next 2
    And the user chooses boy or girl
    And the user clicks next 3
    And the user chooses year
    And the user chooses month
    And the user clicks finish
    Then the user is registered

    Examples:
      |username|name|email|password|
      |PaquitoProblemas|Paco|hafasdf456@wikfee.com|kasdgf32|
