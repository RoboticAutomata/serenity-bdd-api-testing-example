Feature: The user endpoint works as intended
  The user endpoint can handle various 
  Add, Get, Update, Log in, Log out
  Delete requests

  @dev
  Scenario: Add a new user, login, logout
    Given I add a new user
    When I login
    Then I logout
