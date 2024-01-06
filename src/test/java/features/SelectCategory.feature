Feature: Select Category

  Scenario Outline: Select Category

    Given the user is in the main page to select a game category
    When the user clicks in all the categories
    And select <category>
    Then checking <category> title of the web page

    Examples:
    |category|
    |Juegos de Deportes|
    |Juegos para Niñas|

