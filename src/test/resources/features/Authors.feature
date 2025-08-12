Feature: Authors

  Scenario: Retrieve all authors
    When I retrieve all authors
    Then the response status code should be 200
    And the response should contain a list of authors

  Scenario: Retrieve an author by valid ID
    When I retrieve the author with ID: 1
    Then the response status code should be 200
    And the response should contain the author details

  Scenario: Retrieve an author by invalid ID
    When I retrieve the author with ID: 999
    Then the response status code should be 404
    And the response should contain an error message with title: "Not Found"
#
  Scenario Outline: Retrieve an author by valid book ID
    When I retrieve the authors by book ID: <book_id>
    Then the response status code should be 200
    And the response should contain a list of authors
#    And the response should contain value: "<book_id>" at path: "idBook"
    Examples:
      | book_id |
      | 2       |

  Scenario: Retrieve an author by invalid book ID
    When I retrieve the authors by book ID: 10000
    Then the response status code should be 200
    And the response body should be an empty array

  Scenario Outline: Create an author with valid data
    When I create an author with ID: <id>, Book ID: <book_id>, First Name: <first_name>, Last Name: <last_name>
    Then the response status code should be 200
    And the response should contain an author with ID: <id>, Book ID: <book_id>, First Name: <first_name>, Last Name: <last_name>
    Examples:
      | id  | book_id | first_name      | last_name |
      | 300 | 100     | "John"          | "Doe"     |

  Scenario Outline: Create an author with invalid data
    When I create an author with ID: <id>, Book ID: <book_id>, First Name: <first_name>, Last Name: <last_name>
    Then the response status code should be 400
    And the response should contain an error message with title: <error_title>
    And the response should contain value: <error_message> at path: <path>
    Examples:
      | id          | book_id | first_name      | last_name | error_title                               | error_message                                                                                                   | path            |
      | 12345678910 | 100     | "John"          | "Doe"     | "One or more validation errors occurred." | "The JSON value could not be converted to System.Int32. Path: $.id \| LineNumber: 0 \| BytePositionInLine: 17." | "errors.'$.id'" |

  Scenario Outline: Update a book with valid data
    When I update the author with ID: <id>, with New ID: <new_id>, Book ID: <book_id>, First Name: "<first_name>", Last Name: "<last_name>"
    Then the response status code should be 200
    And the response should contain an author with ID: <new_id>, Book ID: <book_id>, First Name: "<first_name>", Last Name: "<last_name>"
    Examples:
      | id  | new_id | book_id | first_name      | last_name |
      | 300 | 301    | 100     | John            | Doe       |
#
  Scenario Outline: Update a book with invalid data
    When I update the author with ID: <id>, with New ID: <new_id>, Book ID: <book_id>, First Name: <first_name>, Last Name: <last_name>
    Then the response status code should be 400
#    And the response should contain value: "One or more validation errors occurred." at path: "title"
    And the response should contain value: <error_message> at path: <path>
    Examples:
      | id          | new_id | book_id | first_name      | last_name | error_message                           | path        |
      | 12345678910 | 3001   | 100     | "John"          | "Doe"     | "The value '12345678910' is not valid." | "errors.id" |
      #| 1000        | New   | Test         | 100        | test    | 2025-04-03T00:00:00 | One or more validation errors occurred. | The JSON value could not be converted to System.Int32. Path: $.id . |

  Scenario: Delete an author with valid ID
    When I delete the author with ID: 100
    Then the response status code should be 200

  Scenario Outline: Delete an author with invalid ID
    When I delete the author with ID: <id>
    Then the response status code should be 400
    And the response should contain an error message with title: "One or more validation errors occurred."
    And the response should contain value: "The value '<id>' is not valid." at path: "errors.id"
    Examples:
      | id          |
      | 12345678910 |