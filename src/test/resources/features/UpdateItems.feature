@todo @updateItems
Feature: Update Todo Items

  Background:
    Given user launches Todo application

  Scenario: Mark new todo item as Completed - Single item

    When I add todo item "Pay rental"
    Then I should see todo list size as 1
    When I mark "Pay rental" item as completed
    Then I should see "Pay rental" item should be striked through
    And I should see "Pay rental" item should be grayed out
    And I should see "Clear completed" button

  Scenario: Mark new todo item as Completed - Multiple items

    When I add below todo items:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |
    Then I should see todo list size as 3
    When I complete below todo items:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |
    Then I should see todo list with below items as striked through and grayed out:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |
    And I should see "Clear completed" button

  Scenario: Edit todo item - Single item

    When I add todo item "Pay rental"
    Then I should see todo list size as 1
    And I should see todo list with item "Pay rental"
    When I update "Pay rental" item as "Pay Rental Updated"
    Then I should not see todo list with item "Pay rental"
    And I should see todo list with item "Pay Rental Updated"

  Scenario: Edit todo item - Multiple items

    When I add below todo items:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |
    Then I should see todo list size as 3
    When I update "Pay rental" item as "Pay Rental Updated"
    When I update "Make CC payment" item as "Credit Card payment"
    Then I should not see todo list with item "Pay rental"
    Then I should not see todo list with item "Make CC payment"
    Then I should see todo list size as 3
    And I should see todo list with below items:
      | Credit Card payment |
      | Pay Rental Updated  |
      | Pay EMI payment     |