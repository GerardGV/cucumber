Feature: Add Game to Favourite
  Scenario Outline: Add Game to Favourite

    Given the user is logged with its <userEmail> and <userPassword> in and in the main page
    When the user searches a <game>, selects its <gameIdentifier>, searches it
    And the user clicks to add the game to favourites
    And the user checks its favourites in his profile
    Then the user has the game in favourites and deletes it from there

    Examples:
      |userEmail|userPassword|game|gameIdentifier|
      |b7bede658dcca4@crankymonkey.info|idafsghbg12345|Geometry Jump|item_210274|