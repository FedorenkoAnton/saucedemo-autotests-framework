Feature: Sorting feature

  @sortFromZtoA @UseAfterHook
  Scenario Outline: As a Saucedemo user I want to sort items alphabetically in ascending order
    Given user choose '<browser>'
    And opens Login page
    And enters user_name 'standard_user'
    And enters password
    And clicks Login button
    And clicks on Product sort dropdown
    When chooses option NAME Z TO A
    Then products should be ordered in alphabetically descending order

    Examples:
      | browser |
      | chrome  |
      | edge    |
      | firefox |

  @sortFromLowToHigh @UseAfterHook
  Scenario Outline: As a Saucedemo user I want to sort items by price from low to high
    Given user choose '<browser>'
    And opens Login page
    And enters user_name 'standard_user'
    And enters password
    And clicks Login button
    And clicks on Product sort dropdown
    When chooses option price low to high
    Then products should be ordered by price from low to high

    Examples:
      | browser |
      | chrome  |
      | edge    |
      | firefox |