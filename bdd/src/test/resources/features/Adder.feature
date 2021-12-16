Feature: Adder
  As a user
  I want to use a calculator to add numbers
  To make life easier

  Scenario: Add two numbers 1 & 2
    Given I have a calculator
    When I add 1 and 2
    Then the result should be 3

  Scenario: Add two numbers 2 & 2
    Given I have a calculator
    When I add 2 and 2
    Then the result should be 4