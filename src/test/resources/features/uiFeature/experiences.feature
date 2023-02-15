Feature: Compare UI and API Experiences
  @wip
  Scenario Outline: UI and API Experiences
    Given User creates a POST request for add an experience with "<title>" "<company>" "<location>" "<from>"  "<to>" "<current>" "<description>"
    Then Verify that status code should be 201
    And Compiler gets the experience id
    And User creates GET request to get experience with id
    And User is on the dashboard page
    Then verify that UI experience and API experience must be matched on company as "Micro"

    Examples:
      | title | company | location | from       | to         | current | description   |
      | SDET  | Micro   | Japan    | 2011-10-10 | 2012-10-10 | false   | It was so far |