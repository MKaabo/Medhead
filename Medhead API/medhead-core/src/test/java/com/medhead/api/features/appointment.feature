@appointment
Feature: Test all appointments rest endpoints

  Background:
    Given The application is available at "http://localhost:8080"
    And Health check is fine at "/actuator/health"

  Scenario: List all available appointments
    When I fetch appointments at "/appointment"
    Then I should find 2 appointments