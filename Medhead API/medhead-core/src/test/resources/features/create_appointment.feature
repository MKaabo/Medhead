Feature: Create appointment

  Background:
    Given a patient who needs an appointment
    And a doctor available

  Scenario: Create an appointment
    When a patient schedules an appointment
    Then it creates an appointment correctly
    Then we are able to modify the appointment