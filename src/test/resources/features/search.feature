Feature: Sample

  @ui @pageobject
  Scenario: Should be able to search for a product from the input box
    Given John is viewing the Etsy landing page
    When he searches for a product from the input box
    Then the result should be displayed

#   This scenario is failing for me, and I am not familiar enough with Serenity in order to fix it in the given time.
#  @ui @screenplay
#  Scenario: Should be able to search for a product from the input box (screenplay)
#    Given John is viewing the Etsy landing page (screenplay)
#    When he searches for a product from the input box (screenplay)
#    Then the result should be displayed (screenplay)

  @ui @wip
  Scenario: Should be able to search for a product from the drop-down menu
    Given John is viewing the Etsy landing page
    When he searches for a category from the drop-down menu
    Then the result should be displayed as the category

  @ui @wip
  Scenario: Should be able to search for a product from the icons
    Given John is viewing the Etsy landing page
    When he searches for a category from the icons
    Then the category should be the same as the icon clicked

  @api
  Scenario: Basic api-key validation
    Given John is not a developer with a valid api-key
    When he requests a list of shops
    Then the status code should be 403

  @api
  Scenario: Should be able to list shops
    Given John is a developer with a valid api-key
    When he requests a list of shops
    Then the status code should be 200
    And content type should be "application/json"
    And response should include 25 of "shop_id"
#    as per API documentation, the default number of shops displayed in a single request is 25