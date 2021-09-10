Feature: create new project request

  Background:
    Given I am authorized

  Scenario:
    Given I am at create new project page
    When I fill name field
    And I select organization
    And I select business  unit
    And I select curator
    And I select leader
    And I select manager
    And I fill main contact field
    And I select main contact
    And I fill planing
    And I fill requirements
    And i fill development
    And I fill testing
    And I fill config management
    And I select skip speed
    And I click save and close button
    Then Success message is visible