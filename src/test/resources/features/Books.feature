Feature: Books

  Scenario: Retrieve all books
    When I retrieve all books
    Then the response status code should be 200
    And the response should contain a list of books

  Scenario: Retrieve a book by valid ID
    When I retrieve the book with ID: 1
    Then the response status code should be 200
    And the response should contain the book details

  Scenario: Retrieve a book by invalid ID
    When I retrieve the book with ID: 999
    Then the response status code should be 404
    And the response should contain an error message with title: "Not Found"

  Scenario Outline: Create a book with valid data
    When I create a book with ID: <id>, Title: <title>, Description: <description>, Page Count: <page_count>, Excerpt: <excerpt>, Publish Date: <publish_date>
    Then the response status code should be 200
    And the response should contain a book with ID: <id>, Title: <title>, Description: <description>, Page Count: <page_count>, Excerpt: <excerpt>, Publish Date: <publish_date>
    Examples:
      | id  | title    | description      | page_count | excerpt | publish_date        |
      | 300 | New Book | Book description | 100        | test    | 2025-04-03T00:00:00 |

  Scenario Outline: Create a book with invalid data
    When I create a book with ID: <id>, Title: <title>, Description: <description>, Page Count: <page_count>, Excerpt: <excerpt>, Publish Date: <publish_date>
    Then the response status code should be 400
    And the response should contain an error message with title: <error_title>
    And the response should contain value: <error_message> at path: <path>
    Examples:
      | id          | title    | description        | page_count  | excerpt           | publish_date        | error_title                               | error_message                                                                                                             | path                     |
      | 12345678910 | New Book | Long ID Book       | 100         | test              | 2025-04-03T00:00:00 | "One or more validation errors occurred." | "The JSON value could not be converted to System.Int32. Path: $.id \| LineNumber: 0 \| BytePositionInLine: 17."           | "errors.'$.id'"          |
      | 300         | New Book | Long page count    | 12345678910 | test              | 2025-04-03T00:00:00 | "One or more validation errors occurred." | "The JSON value could not be converted to System.Int32. Path: $.pageCount \| LineNumber: 0 \| BytePositionInLine: 84."    | "errors.'$.pageCount'"   |

  Scenario Outline: Update a book with valid data
    When I update the book with ID: <id> with New ID: <new_id>, Title: <title>, Description: <description>, Page Count: <page_count>, Excerpt: <excerpt>, Publish Date: <publish_date>
    Then the response status code should be 200
    And the response should contain a book with ID: <new_id>, Title: <title>, Description: <description>, Page Count: <page_count>, Excerpt: <excerpt>, Publish Date: <publish_date>
    Examples:
      | id  | new_id | title        | description      | page_count | excerpt | publish_date        |
      | 100 | 101    | Updated Book | Book Description | 100        | Update  | 2025-04-03T00:00:00 |

  Scenario Outline: Update a book with invalid data
    When I update the book with ID: <id> with New ID: <new_id>, Title: "<title>", Description: "<description>", Page Count: <page_count>, Excerpt: "<excerpt>", Publish Date: "<publish_date>"
    Then the response status code should be 400
    And the response should contain an error message with title: "<error_title>"
    And the response should contain value: <error_message> at path: <path>
    Examples:
      | id          | new_id      | title | description  | page_count | excerpt | publish_date        | error_title                             | error_message                                                                                                   | path            |
      | 12345678910 | 100         | New   | Long ID Book | 100        | test    | 2025-04-03T00:00:00 | One or more validation errors occurred. | "The value '12345678910' is not valid."                                                                         | "errors.id"     |
      | 100         | 12345678910 | New   | Long New ID  | 100        | test    | 2025-04-03T00:00:00 | One or more validation errors occurred. | "The JSON value could not be converted to System.Int32. Path: $.id \| LineNumber: 0 \| BytePositionInLine: 17." | "errors.'$.id'" |

  Scenario: Delete a book with valid ID
    When I delete the book with ID: 100
    Then the response status code should be 200

  Scenario Outline: Delete a book with invalid ID
    When I delete the book with ID: <id>
    Then the response status code should be 400
    And the response should contain an error message with title: "One or more validation errors occurred."
    And the response should contain value: "The value '<id>' is not valid." at path: "errors.id"
    Examples:
      | id          |
      | 12345678910 |