Feature: Adding and removing products

  @addItemsToCart @UseAfterHook
  Scenario Outline: As a Saucedemo user I want to add products to the cart and find them there
    Given user choose '<browser>'
    And opens Login page
    And enters user_name 'standard_user'
    And enters password
    And clicks Login button
    When user adds 'Sauce Labs Backpack' to the cart
    And user adds 'Sauce Labs Fleece Jacket' to the cart
    And user adds 'Sauce Labs Onesie' to the cart
    And clicks on Cart icon
    Then added products are displayed on the cart page

    Examples:
      | browser |
      | chrome  |
      | edge    |
      | firefox |

  @removeAddedItemsFromCart @UseAfterHook
  Scenario Outline: As a Saucedemo user I want to remove accidentally added products from the cart
    Given user choose '<browser>'
    And opens Login page
    And enters user_name 'standard_user'
    And enters password
    And clicks Login button
    When user adds 'Sauce Labs Backpack' to the cart
    And user adds 'Sauce Labs Fleece Jacket' to the cart
    And user adds 'Sauce Labs Onesie' to the cart
    And clicks on Cart icon
    And user remove 'Sauce Labs Fleece Jacket' from the cart
    Then 'Sauce Labs Fleece Jacket' is no longer in the cart

    Examples:
      | browser |
      | chrome  |
      | edge    |
      | firefox |

    @UseAfterHook
    Scenario Outline: As a SauceDemo user I want to see amount of products added to the cart
      Given user choose '<browser>'
      And opens Login page
      And enters user_name 'standard_user'
      And enters password
      And clicks Login button
      When user adds 'Sauce Labs Backpack' to the cart
      And user adds 'Sauce Labs Fleece Jacket' to the cart
      And user adds 'Sauce Labs Onesie' to the cart
      Then user see amount of products on cart icon is 3

      Examples:
        | browser |
        | chrome  |
        | edge    |
        | firefox |