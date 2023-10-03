Feature: Order pet

  Scenario Outline: As a pet store customer I want to place an order for a pet
    Given user sets up the endpoint /pet/findByStatus
    And user sets up pet available status to the request
    And sets up accepted media type <media type>
    And sends request to 'find pets by status endpoint' with http method Get
    When response is got service code should be 200
    And user picks random pet
    And user sets up the endpoint /store/order
    And sets up request body <content type>
    And sets up accepted media type <media type>
    And sets up pet id to request body located at <request's body location>
    And assigns request body located at <request's body location>
    And sends request to 'place an order for a pet' with http method Post
    Then response is got service code should be 200
    And id of ordered pet should be equal to picked pet id
    And status field from response body should be 'placed'
    And complete field from response body should be 'true'

    Examples:
      | media type       | content type     | request's body location                    |
      | application/json | application/json | json/place_order_for_pet_request_body.json |
      | application/xml  | application/json | json/place_order_for_pet_request_body.json |