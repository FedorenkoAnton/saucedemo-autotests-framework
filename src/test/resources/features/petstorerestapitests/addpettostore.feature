Feature: Add new pet to the store

  Scenario Outline: As a petStore user I want to add new pet to the store
    Given user sets up the <endpoint>
    And sets up request body <content type>
    And sets up accepted <media type>
    And assigns request body
    And sends request with Post method
    When response is got service code should be <expected response code>
    And response body is not empty
    Then response body's field id should be 9999
    And response body's field name should be spankey

    Examples:
      | endpoint | content type     | media type       | expected response code |
      | /pet     | application/json | application/json | 200                    |
