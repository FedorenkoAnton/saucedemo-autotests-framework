Feature: Add new pet to the store

  Scenario Outline: As a petStore user I want to add new pet to the store
    Given user sets up the endpoint /pet
    And sets up request body <content type>
    And sets up accepted media type <media type>
    And assigns request body located at <request's body location>
    And sends request to 'store pet endpoint' with http method Post
    When response is got service code should be 200
    And response body is not empty
    Then response body's field id should be 9999
    And response body's field name should be spankey

    Examples:
      | content type     | media type       | request's body location            |
      | application/json | application/json | json/add_new_pet_request_body.json |
      | application/xml  | application/xml  | xml/add_new_pet_request_body.xml   |


  Scenario: As a petStore user I want to add image to the lot
    Given user set up pet id 9999
    And user sets up the endpoint /pet/{{petId}}/uploadImage
    And sets up request body multipart/form-data
    And sets up accepted media type application/json
    And assigns request body located at images/dog.jpg
    And sends request to 'store pet endpoint' with http method Post
    When response is got service code should be 200
    Then response body is not empty
    And status code from response body is 200
    And file size is 212377 bytes