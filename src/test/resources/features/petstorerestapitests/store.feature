Feature: Order pet

  Scenario Outline: As a pet store customer I want to place an order for a pet
    Given user sets up the endpoint /pet/findByStatus
    And user sets up pet available status to the request
    And sets up accepted media type <media type>
    And sends request to 'find pets by status endpoint' with http method Get
    When response is got service code should be 200
    And user picks random pet
    And user sets up the endpoint /store/order
    And sets up request body application/json
    And sets up accepted media type <media type>
    And sets up pet id to request body located at json/place_order_for_pet_request_body.json
    And assigns request body located at json/place_order_for_pet_request_body.json
    And sends request to 'place an order for a pet' with http method Post
    Then response is got service code should be 200
    And id of ordered pet should be equal to picked pet id
    And status field from response body should be 'placed'
    And complete field from response body should be 'true'

    Examples:
      | media type       |
      | application/json |
      | application/xml  |

  Scenario Outline: As a pet store customer I want to find purchase order by order ID
    Given user sets up the endpoint /store/order
    And sets up request body application/json
    And sets up accepted media type <media type>
    And sets up pet id as 3 to request body located at json/place_order_for_pet_request_body.json
    And assigns request body located at json/place_order_for_pet_request_body.json
    When sends request to 'place an order for a pet' with http method Post
    And response is got service code should be 200
    And user set up orderId 3
    And user sets up the endpoint /store/order/{{orderId}}
    And sets up accepted media type <media type>
    And sends request to 'find purchase order by ID' with http method Get
    Then response is got service code should be 200
    And 'id' field in response body should be 3
    And status field from response body should be 'placed'
    And complete field from response body should be 'true'

    Examples:
      | media type       |
      | application/json |
      | application/xml  |