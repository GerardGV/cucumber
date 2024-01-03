Feature: Change Language
  Scenario Outline: Change Language

    Given the user starts the main page
    When the user clicks in the spanish flag
    And the user clicks a new <language> option
    Then Text Iniciar sesion has change to Log In

    Examples:
    |language|
    |English|