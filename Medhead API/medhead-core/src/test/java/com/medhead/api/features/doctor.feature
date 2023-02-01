@doctor
Feature: Test all doctors rest endpoints

  Background:
    Given The application is available at "http://localhost:8080"
    And Health check is fine at "/actuator/health"

  Scenario: List all available doctors
    When I fetch doctors at "/doctor"
    Then I should find 2 doctors