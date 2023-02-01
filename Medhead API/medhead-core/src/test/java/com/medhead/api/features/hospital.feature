@hospital
Feature: Test all hosppitals rest endpoints

  Background:
    Given The application is available at "http://localhost:8080"
    And Health check is fine at "/actuator/health"

  Scenario: List all available hospitals
    When I fetch hospitals at "/hospital"
    Then I should find 2 hospitals