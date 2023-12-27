Feature: Russian Car Driver
Scenario Outline: Show Russian Car Driver

  Given the user is in the index page to search a game
  When the user clicks conducir options
  And the user enters <article> in the search bar
  And the user clicks the search button
  Then the <article> list appears

Examples:
  |article|
  |Russian car driver hd|
  |Traffic Jam 3d|