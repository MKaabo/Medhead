@hospital
Feature: Test all emergencies rest endpoints

  Background:
    Given The application is available at "http://localhost:8080"
    And Health check is fine at "/actuator/health"

  Scenario: List all available emergencies
    When I fetch emergencies at "/emergency"
    Then I should find 2 emergencies