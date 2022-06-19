@todo @deleteItems
Feature: Delete Todo Items

  Background:
    Given user launches Todo application

  Scenario: Delete new todo item - Single item

    When I add todo item "Complete Singtel Task"
    Then I should see todo list size as 1
    And I should see footer displays number of todo items left
    And I should see footer have "All", "Active" and "Completed" buttons
    When I delete the item "Complete Singtel Task"
    Then I should see todo list size as 0
    And I should not see footer

  Scenario: Delete todo items - Multiple items

    When I add below todo items:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |
    Then I should see todo list size as 3
    And I delete below todo items:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |
    Then I should see todo list size as 0
    And I should not see footer

  Scenario: Delete some todo items - random items

    When I add below todo items:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |
    Then I should see todo list size as 3
    When I delete below todo items:
      | Make CC payment |
      | Pay EMI payment |
    Then I should see todo list size as 1
    Then I should see footer displays number of todo items left
    And I should see todo list with item "Pay rental"
    And I should see footer

  Scenario: Delete completed item - single item

    When I add below todo items:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |
    Then I should see todo list size as 3
    When I mark "Pay rental" item as completed
    Then I should see todo list size as 3
    When I delete the item "Pay rental"
    Then I should see todo list size as 2
    And I should not see todo list with item "Pay rental"
    And I should see footer

  Scenario: Delete completed items - Multiple items

    When I add below todo items:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |
    Then I should see todo list size as 3
    When I mark "Pay rental" item as completed
    And I mark "Make CC payment" item as completed
    Then I should see todo list size as 3
    When I delete the item "Pay rental"
    When I delete the item "Make CC payment"
    And I should not see todo list with item "Pay rental"
    And I should not see todo list with item "Make CC payment"
    Then I should see todo list size as 1
    And I should see footer

  Scenario: Delete completed item using clear completed Button - single item

    When I add below todo items:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |
    Then I should see todo list size as 3
    When I mark "Pay rental" item as completed
    Then I should see todo list size as 3
    When I click on "Clear completed" button
    Then I should see todo list size as 2
    And I should not see todo list with item "Pay rental"
    And I should see footer

  Scenario: Delete completed items using clear completed Button - Multiple items

    When I add below todo items:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |
    Then I should see todo list size as 3
    When I mark "Pay rental" item as completed
    And I mark "Make CC payment" item as completed
    Then I should see todo list size as 3
    When I click on "Clear completed" button
    And I should not see todo list with item "Pay rental"
    And I should not see todo list with item "Make CC payment"
    Then I should see todo list size as 1
    And I should see footer

