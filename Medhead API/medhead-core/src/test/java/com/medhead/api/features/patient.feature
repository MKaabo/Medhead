@patient
Feature: Test all patients rest endpoints

  Background:
    Given The application is available at "http://localhost:8080"
    And Health check is fine at "/actuator/health"

  Scenario: List all available patients
    When I fetch patients at "/patient"
    Then I should find 2 patients