Feature: Smoke path

  @SmokePath @UseAfterHook
  Scenario Outline: As a Saucedemo customer I want to order and checkout desired product
    Given user choose '<browser>'
    And opens Login page
    And enters user_name 'standard_user'
    And enters password
    And clicks Login button
    When user adds 'Sauce Labs Backpack' to the cart
    And clicks on Cart icon
    And clicks on Checkout button
    And enters first name 'Glen'
    And enters last name 'Quagmire'
    And enters postal code '61001'
    And clicks Checkout button
    When clicks on Finish button
    Then Complete header is on Checkout complete page and matches the expected 'Thank you for your order!'
    And Complete text is on Checkout complete page and matches the expected 'Your order has been dispatched, and will arrive just as fast as the pony can get there!'

    Examples:
      | browser |
      | chrome  |
      | edge    |
      | firefox |