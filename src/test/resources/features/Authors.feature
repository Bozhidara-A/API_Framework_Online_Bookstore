Feature: Authors

  Scenario: Retrieve all authors
    When I retrieve all authors
    Then the response status code should be 200
    And the response should contain a list of authors

  Scenario Outline: Retrieve an author by valid ID
    Given an author with ID <id> exists
    When I retrieve the author with ID: <id>
    Then the response status code should be 200
    And the response should contain the author details
    Examples:
      | id |
      | 1  |

  Scenario Outline: Retrieve a non existing author
    Given an author with ID <id> does not exist
    When I retrieve the author with ID: <id>
    Then the response status code should be 404
    And the response should contain an error message with title: "Not Found"
    Examples:
      | id  |
      | 999 |

  Scenario Outline: Retrieve an author by invalid ID
    When I retrieve the author with ID: <id>
    Then the response status code should be 400
    And the response should contain an error message with title: "One or more validation errors occurred."
    And the response should contain value: "The value '<id>' is not valid." at path: "errors.id"
    Examples:
      | id          |
      | 12345678910 |

  Scenario Outline: Retrieve an author by valid book ID
    Given a book with ID <book_id> exists
    When I retrieve the authors by book ID: <book_id>
    Then the response status code should be 200
    And the response should contain a list of authors
    Examples:
      | book_id |
      | 2       |

  Scenario Outline: Retrieve an author by not existing book ID
    Given a book with ID <book_id> does not exist
    When I retrieve the authors by book ID: <book_id>
    Then the response status code should be 404
    Examples:
      | book_id |
      | 400     |

  Scenario Outline: Retrieve an author by invalid book ID
    When I retrieve the authors by book ID: <book_id>
    Then the response status code should be 400
    And the response should contain an error message with title: "One or more validation errors occurred."
    And the response should contain value: "The value '<book_id>' is not valid." at path: "errors.idBook"
    Examples:
      | book_id     |
      | 12345678910 |

  Scenario Outline: Create an author with valid data
    Given an author with ID <id> does not exist
    And a book with ID <book_id> exists
    When I create an author with ID: <id>, Book ID: <book_id>, First Name: <first_name>, Last Name: <last_name>
    Then the response status code should be 200
    And the response should contain an author with ID: <id>, Book ID: <book_id>, First Name: <first_name>, Last Name: <last_name>
    Examples:
      | id  | book_id | first_name | last_name |
      | 300 | 100     | "John"     | "Doe"     |

  Scenario Outline: Create an author with already existing ID
    Given an author with ID <id> exists
    When I create an author with ID: <id>, Book ID: <book_id>, First Name: <first_name>, Last Name: <last_name>
    Then the response status code should be 409
    Examples:
      | id  | book_id | first_name | last_name |
      | 300 | 100     | "John"     | "Doe"     |

  Scenario Outline: Create an author with not existing book ID
    Given an author with ID <id> does not exist
    And a book with ID <book_id> does not exist
    When I create an author with ID: <id>, Book ID: <book_id>, First Name: <first_name>, Last Name: <last_name>
    Then the response status code should be 404
    Examples:
      | id  | book_id | first_name | last_name |
      | 300 | 100     | "John"     | "Doe"     |

  Scenario Outline: Create an author with invalid data
    When I create an author with ID: <id>, Book ID: <book_id>, First Name: <first_name>, Last Name: <last_name>
    Then the response status code should be 400
    And the response should contain an error message with title: <error_title>
    And the response should contain value: <error_message> at path: <path>
    Examples:
      | id          | book_id     | first_name | last_name | error_title                               | error_message                                                                                                       | path                |
      | 12345678910 | 100         | "John"     | "Doe"     | "One or more validation errors occurred." | "The JSON value could not be converted to System.Int32. Path: $.id \| LineNumber: 0 \| BytePositionInLine: 17."     | "errors.'$.id'"     |
      | 300         | 12345678910 | "John"     | "Doe"     | "One or more validation errors occurred." | "The JSON value could not be converted to System.Int32. Path: $.idBook \| LineNumber: 0 \| BytePositionInLine: 30." | "errors.'$.idBook'" |

  Scenario Outline: Update an author with valid data
    Given an author with ID <id> exists
    And an author with ID <new_id> does not exist
    And a book with ID <book_id> exists
    When I update the author with ID: <id>, with New ID: <new_id>, Book ID: <book_id>, First Name: "<first_name>", Last Name: "<last_name>"
    Then the response status code should be 200
    And the response should contain an author with ID: <new_id>, Book ID: <book_id>, First Name: "<first_name>", Last Name: "<last_name>"
    Examples:
      | id  | new_id | book_id | first_name | last_name |
      | 10  | 301    | 100     | John       | Doe       |

  Scenario Outline: Update an author ID to already existing ID
    Given an author with ID <id> exists
    And an author with ID <new_id> exists
    And a book with ID <book_id> exists
    When I update the author with ID: <id>, with New ID: <new_id>, Book ID: <book_id>, First Name: "<first_name>", Last Name: "<last_name>"
    Then the response status code should be 409
    Examples:
      | id  | new_id | book_id | first_name | last_name |
      | 10  | 12     | 100     | John       | Doe       |

  Scenario Outline: Update an author with not existing book ID
    Given an author with ID <id> exists
    And an author with ID <new_id> does not exist
    And a book with ID <book_id> does not exist
    When I update the author with ID: <id>, with New ID: <new_id>, Book ID: <book_id>, First Name: "<first_name>", Last Name: "<last_name>"
    Then the response status code should be 404
    Examples:
      | id  | new_id | book_id | first_name | last_name |
      | 10  | 12     | 900     | John       | Doe       |

  Scenario Outline: Update an author with invalid data
    When I update the author with ID: <id>, with New ID: <new_id>, Book ID: <book_id>, First Name: <first_name>, Last Name: <last_name>
    Then the response status code should be 400
    And the response should contain an error message with title: "One or more validation errors occurred."
    And the response should contain value: <error_message> at path: <path>
    Examples:
      | id          | new_id      | book_id     | first_name | last_name | error_message                                                                                                       | path                |
      | 12345678910 | 3001        | 100         | "John"     | "Doe"     | "The value '12345678910' is not valid."                                                                             | "errors.id"         |
      | 1000        | 12345678910 | 100         | "John"     | "Doe"     | "The JSON value could not be converted to System.Int32. Path: $.id \| LineNumber: 0 \| BytePositionInLine: 17."     | "errors.'$.id'"     |
      | 100         | 3001        | 12345678910 | "John"     | "Doe"     | "The JSON value could not be converted to System.Int32. Path: $.idBook \| LineNumber: 0 \| BytePositionInLine: 31." | "errors.'$.idBook'" |

  Scenario Outline: Delete an author with valid ID
    Given an author with ID <id> exists
    When I delete the author with ID: <id>
    Then the response status code should be 200
    Examples:
    | id |
    | 1  |

  Scenario Outline: Delete a not existing author
    Given an author with ID <id> does not exist
    When I delete the author with ID: <id>
    Then the response status code should be 404
    And the response should contain an error message with title: "Not Found"
    Examples:
      | id  |
      | 400 |

  Scenario Outline: Delete an author with invalid ID
    When I delete the author with ID: <id>
    Then the response status code should be 400
    And the response should contain an error message with title: "One or more validation errors occurred."
    And the response should contain value: "The value '<id>' is not valid." at path: "errors.id"
    Examples:
      | id          |
      | 12345678910 |