Feature: Validate GET /api/users/2 API Response
  As a developer
  I want to ensure that the GET /api/users/2 API returns the correct user data
  So that I can verify the integrity of the API response.

  # Test Type: Functional
  Scenario: Verify Response Status Code
    Given I send a GET request to "/api/users/2"
    When I receive the response
    Then the response status code should be 200

  # Test Type: Functional
  Scenario: Verify User ID in Response
    Given I send a GET request to "/api/users/2"
    When I receive the response
    Then the user object should have an ID of 2

  # Test Type: Functional
  Scenario: Verify User Object Fields
    Given I send a GET request to "/api/users/2"
    When I receive the response
    Then the user object should contain the following fields:
      | id       |
      | email    |
      | first_name |
      | last_name |
      | avatar   |

  # Test Type: Functional
  Scenario: Verify Email Field
    Given I send a GET request to "/api/users/2"
    When I receive the response
    Then the email field should be "user2@example.com"

  # Test Type: Functional
  Scenario: Verify First Name Field
    Given I send a GET request to "/api/users/2"
    When I receive the response
    Then the first_name field should be "John"

  # Test Type: Functional
  Scenario: Verify Last Name Field
    Given I send a GET request to "/api/users/2"
    When I receive the response
    Then the last_name field should be "Doe"

  # Test Type: Functional
  Scenario: Verify Avatar Field
    Given I send a GET request to "/api/users/2"
    When I receive the response
    Then the avatar field should contain a valid URL

  # Test Type: Negative
  Scenario: Verify Invalid User ID
    Given I send a GET request to "/api/users/999"
    When I receive the response
    Then the response status code should be 404
