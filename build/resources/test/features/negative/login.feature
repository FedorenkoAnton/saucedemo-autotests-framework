Feature: Login feature

  @loginEmptyCreds
  Scenario Outline: As a Sauce demo user I cannot log in with empty credentials
    Given user choose '<browser>'
    And opens Login page
    And enters user_name ''
    And enters password ''
    When clicks Login button
    Then error message is displayed
    And error message contains text 'Epic sadface: Username is required'

    Examples:
      | browser |
      | chrome  |
      | edge    |
      | firefox |

  @loginLockedUser
  Scenario Outline: As locked out user I should not log in to Sauce demo
    Given user choose '<browser>'
    And opens Login page
    And enters user_name 'locked_out_user'
    And enters password
    When clicks Login button
    Then error message is displayed
    And error message contains text 'Epic sadface: Sorry, this user has been locked out.'

    Examples:
      | browser |
      | chrome  |
      | edge    |
      | firefox |