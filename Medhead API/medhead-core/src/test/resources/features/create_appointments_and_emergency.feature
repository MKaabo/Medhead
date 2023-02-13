Feature: Create appointments and emergencies

  Background:
    Given a patient exists
    And a doctor exists with an existing hospital

  Scenario: Create an appointment
    When a patient schedules an appointment
    Then it creates an appointment correctly

  Scenario: Create an emergency
    When a patient is in a medical emergency
    Then The correct hospital should be found
    Then it creates an emergency correctly