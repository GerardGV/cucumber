Feature: Watch all labels

  Scenario: Watch all labels

    Given the user is in main page to select to watch all the categories
    When the user clicks in all the labels
    Then checking if appears the header with the number of labels,categories
