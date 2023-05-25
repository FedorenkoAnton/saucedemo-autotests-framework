Feature: Add new pet to the store

  Scenario Outline: As a petStore user I want to add new pet to the store
    Given user sets up the <endpoint>
    And sets up request body <content type>
    And sets up accepted <media type>
    And assigns <request body>
    And sends request with <http method>
    When response is got service code should be <expected response code>
    And response body is not empty
    Then response body's field id should be 9999
    And response body's field name should be spankey

    Examples:
      | endpoint | content type     | media type       | request body                       | http method | expected response code |
      | /pet     | application/json | application/json | json/add_new_pet_request_body.json | Post        | 200                    |
      | /pet     | application/xml  | application/xml  | xml/add_new_pet_request_body.xml   | Post        | 200                    |


  Scenario Outline: As a petStore user I want to add image to the lot
    Given user set up <petId>
    And user sets up the <endpoint>
    And sets up request body <content type>
    And sets up accepted <media type>
    And assigns <request body>
    And sends request with <http method>
    When response is got service code should be <expected response code>
    Then response body is not empty
    And status code from response body is 200
    And file size is 212377 bytes

    Examples:
      | petId | endpoint                   | content type        | media type       | request body   | http method | expected response code |
      | 9999  | /pet/{{petId}}/uploadImage | multipart/form-data | application/json | images/dog.jpg | Post        | 200                    |