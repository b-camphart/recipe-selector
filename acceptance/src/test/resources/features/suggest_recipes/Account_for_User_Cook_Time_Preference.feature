Feature: Account_for_User_Cook_Time_Preference

  Background:
    Given the following recipes take this much time
      | recipe   | cook time  |
      | Recipe 1 | 45 minutes |
      | Recipe 2 | 30 minutes |
      | Recipe 3 | 1 hour     |
      | Recipe 4 | 0.5 hours  |
    And the potential chef prefers recipes that take 30 minutes or less

  Scenario: Don't suggest recipes that take too long
    When the potential chef requests recipes for the date range of July 20, 2020 to July 21, 2020
    And the following recipes should have been suggested in any order from July 20, 2020 to July 21, 2020
      | Recipe 2 | Recipe 4 |

  Scenario: Additional Recipe Below Limit Not Found
    When the potential chef requests recipes for the date range of July 20, 2020 to July 22, 2020
    And the following recipes should have been suggested in any order from July 20, 2020 to July 21, 2020
      | Recipe 2 | Recipe 4 |
    And Recipe 1 should have been offered as an imperfect match for July 22, 2020

  Scenario: Potential Chef Rejects Imperfect Match
    Given the potential chef has requested recipes for the date range of July 20, 2020 to July 22, 2020
    When the potential chef rejects the imperfect match for July 22, 2020
    And one of following recipes should have been suggested for July 22, 2020
      | Recipe 2 | Recipe 4 |
