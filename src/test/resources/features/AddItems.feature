@todo @addItems
Feature: Add Todo Items

  Background:
    Given user launches Todo application
    
  Scenario: Home Page Validation

    Then I should see header as "todos"
    And I should see text box with placeholder "What needs to be done?"

  Scenario: Add new todo item - Single item

    When I add todo item "Complete Singtel Task"
    Then I should see todo list size as 1
    And I should see todo list with item "Complete Singtel Task"
    And I should see footer displays number of todo items left
    And I should see footer have "All", "Active" and "Completed" buttons

  Scenario: Add new todo items - Multiple items

    When I add below todo items:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |
    Then I should see todo list size as 3
    And I should see todo list with below items:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |
    And I should see footer displays number of todo items left
    And I should see footer have "All", "Active" and "Completed" buttons

  Scenario: Verify todo items insertion order

    When I add below todo items:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |
    Then I should see todo list size as 3
    And I should see todo list with first item as "Make CC payment"
    And I should see todo list with last item as "Pay EMI payment"
    And I should see todo list with below items in same order:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |

  Scenario: Verify Footer links after adding multiple todo items

    When I add below todo items:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |
    Then I should see "All" link selected as default
    When I click on "Active" link
    And I should see todo list with below items:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |
    When I click on "Completed" link
    Then I should see todo list size as 0



