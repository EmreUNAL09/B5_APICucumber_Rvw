Feature: Retrieve Profile

  Scenario: User able to see all profiles
    Given User creates a get request and able to see all profiles
    Then Verify that status code is 200

  Scenario: User able to see all profiles second way
    Given User creates a get request and able to see all profiles second way
    Then Verify that status code is 200

  Scenario Outline: GET request with path param
    Given User creates a GET request with path "<id>"
    Then Verify that status code is 200
    Then Verify that user info's "<name>" and "<email>" and "<company>" and "<location>"
    Examples:
      | id  | name         | email                | company  | location |
      | 236 | Mike Masters | etsMntr@eurotech.com | eurotech | Germany  |
