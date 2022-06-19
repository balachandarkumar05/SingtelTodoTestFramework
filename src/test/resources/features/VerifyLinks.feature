@todo @verifyLinks
Feature: Verify Links in Todo Application

  Background:
    Given user launches Todo application

  Scenario: All Links should display - Single item

    When I add todo item "Pay rental"
    Then I should see todo list size as 1
    And I should see footer have "All", "Active" and "Completed" buttons
    And I should see "All" link selected as default
    When I click on "Active" link
    Then I should see todo list with item "Pay rental"
    When I click on "Completed" link
    Then I should see todo list size as 0

  Scenario: All Links should display - Multiple items

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

  Scenario: Verify ALL link functionality

    When I add below todo items:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |
      | Book ticket     |
    When I complete below todo items:
      | Make CC payment |
      | Pay EMI payment |
    Then I should see "All" link selected as default
    When I click on "Active" link
    And I click on "All" link
    And I should see todo list with below items:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |
      | Book ticket     |

  Scenario: Verify ACTIVE link functionality

    When I add below todo items:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |
      | Book ticket     |
    When I complete below todo items:
      | Make CC payment |
      | Pay EMI payment |
    When I click on "Active" link
    And I should see todo list with below items:
      | Pay rental  |
      | Book ticket |

  Scenario: Verify COMPLETED link functionality

    When I add below todo items:
      | Make CC payment |
      | Pay rental      |
      | Pay EMI payment |
      | Book ticket     |
    When I complete below todo items:
      | Make CC payment |
      | Pay EMI payment |
    When I click on "Completed" link
    And I should see todo list with below items:
      | Make CC payment |
      | Pay EMI payment |