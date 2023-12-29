Feature: Watch Video

  Scenario Outline: Watch Video

    Given the user is in the main page
    When the user clicks in the video section
    And the user search a <video> in specific
    And the user clicks on the search button
    And the user selects the video with its <identification>
    And the user plays the video
    Then video is playing

    Examples:
      |video|identification|
      |Kids Crying For No Reason|item_151835|
      |Funny Kids|item_200005|
