Feature: Edit Profile

  Scenario Outline: Edit User Profile

    Given the user is logged with its <email> and <password> in and in the index page
    When the user clicks in its profile icon
    And the user clicks in the edit profile option
    And the user changes its <identity>, <username>, <name>, <gender>, <birthDateMonthNumber> and <language>
    And the user updates the identity values
    Then the identity is updated

    Examples:
      |email|password|identity|username|name|gender|birthDateMonthNumber|language|
      |b7bede658dcca4@crankymonkey.info|idafsghbg12345|female_identity|PaquitaProblemas|Paca|female|3|eo|
      |b7bede658dcca4@crankymonkey.info|idafsghbg12345|male_identity|NewPaquitoProblemas|PacoGod|male|6|it|
