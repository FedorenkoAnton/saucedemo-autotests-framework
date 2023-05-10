Feature: Checkout feature

  @CheckoutNoCustomerDataProvided @UseAfterHook
  Scenario Outline: As a Sauce demo user I cannot checkout products without entering my data
    Given user choose '<browser>'
    And opens Login page
    And enters user_name 'standard_user'
    And enters password
    And clicks Login button
    When user adds 'Sauce Labs Backpack' to the cart
    And clicks on Cart icon
    And clicks on Checkout button
    When clicks Checkout button
    Then error message on Checkout-step-one page should appear and contain text 'Error: First Name is required'

    Examples:
      | browser |
      | chrome  |
      | edge    |
      | firefox |

  @CheckoutEmptyCart @UseAfterHook
  Scenario Outline: As a Sauce demo user I cannot checkout empty cart
    Given user choose '<browser>'
    And opens Login page
    And enters user_name 'standard_user'
    And enters password
    And clicks Login button
    When clicks on Cart icon
    Then Checkout button should be disabled

    Examples:
      | browser |
      | chrome  |
      | edge    |
      | firefox |