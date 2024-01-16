Feature: Comment On Video
  Scenario Outline: Comment On Video

    Given the user is in the video section and logged in with its <userEmail> and <userPassword>
    When the user searches random <video> and clicks on the <identifier>
    And the user writes its <comment>
    And publishes the comment
    Then the comment is sent to revision

    Examples:
      |userEmail|userPassword|video|identifier|comment|
      |b7bede658dcca4@crankymonkey.info|idafsghbg12345|Kids crying for no reason|item_151835|Please upload more videos like this|

  Scenario Outline: Like Video

    Given the user is in the video section and logged in with its <userEmail> and <userPassword>
    When the user searches random <video> and clicks on the <identifier>
    And the user likes the video
    Then video is liked

    Examples:
      |userEmail|userPassword|video|identifier|
      |b7bede658dcca4@crankymonkey.info|idafsghbg12345|Kids crying for no reason|item_151835|
