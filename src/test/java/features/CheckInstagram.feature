Feature: Checking Instagram
  Scenario Outline: Checking Instagram

    Given the user is in the main page for Social Media test
    When the user clicks <social_media> icon
    Then the title of new web page contains <social_media>

    Examples:
    |social_media|
    |Instagram|
    |Facebook|