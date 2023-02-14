Feature: Create emergency

  Background:
    Given a patient in an emergency
    And hospitals exist

  Scenario: Create an emergency
    When a patient is in a medical emergency
    Then the correct hospital should be found
    Then it creates an emergency correctly
    Then we are able to modify the emergency