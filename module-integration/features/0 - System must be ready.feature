@pre-integration-test
Feature: 0 - INTEGRATION - System must be ready

  @major
  Scenario: Ping app
    Given nothing
    When SYSTEM is up
    Then APP is up and running with code=200
      Then APP.API is up and running with code=200
      Then APP.UI is up and running with code=200
 