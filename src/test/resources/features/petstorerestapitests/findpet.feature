Feature: Find pet in the store

  Scenario Outline: As a pet store customer I want to find pet by Id
    Given user sets up the endpoint /pet
    And sets up request body <content type>
    And sets up accepted media type <media type for Store operation>
    And assigns request body located at <request's body location>
    And sends request to 'store pet endpoint' with http method Post
    And response is got service code should be 200
    And user set up petId 9999
    Then user sets up the endpoint /pet/{{petId}}
    And sets up accepted media type <media type for get pet by id operation>
    And sends request to 'find pet by id endpoint' with http method Get
    When response is got service code should be 200
    And field 'id' from response body is 9999
    And field 'name' from response body is spankey

    Examples:
      | content type     | media type for Store operation | request's body location            | media type for get pet by id operation |
      | application/json | application/json               | json/add_new_pet_request_body.json | application/json                       |
      | application/xml  | application/xml                | xml/add_new_pet_request_body.xml   | application/xml                        |

  Scenario Outline: As a pet store customer I want to find pets by status
    Given user sets up the endpoint /pet/findByStatus
    And user sets up pet <status> status to the request
    And sets up accepted media type <media type>
    And sends request to 'find pets by status endpoint' with http method Get
    When response is got service code should be 200
    Then field 'status' of each pet from response is equal to <status>

    Examples:
      | status    | media type       |
      | available | application/json |
      | available | application/xml  |
      | pending   | application/json |
      | pending   | application/xml  |
      | sold      | application/json |
      | sold      | application/xml  |