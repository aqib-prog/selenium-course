Feature: Login functionality
  Scenario: Valid login
    Given I am on the login page
    When I enter username "standard_user" and password "secret_sauce"
    Then I should be redirected to inventory page

  Scenario: Invalid login
    Given I am on the login page
    When I enter username "wrong_user" and password "wrong_pass"
    Then I should see an error message
