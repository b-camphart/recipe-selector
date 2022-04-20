Feature: Previously_Suggested_Recipes_Are_Reused

  Background:
    Given The following recipes have already been planned
      | recipe       | date          |
      | delicious 1  | July 20, 2020 |
      | delicious 6  | July 21, 2020 |
      | delicious 2  | July 22, 2020 |
      | delicious 14 | July 23, 2020 |
      | repulsive 3  | July 24, 2020 |

  Scenario: Reuse Previously Suggested Recipes
    When the potential chef requests recipes for the date range of July 20, 2020 to July 24, 2020
    Then the following recipes should be suggested
      | recipe       | date          |
      | delicious 1  | July 20, 2020 |
      | delicious 6  | July 21, 2020 |
      | delicious 2  | July 22, 2020 |
      | delicious 14 | July 23, 2020 |
      | repulsive 3  | July 24, 2020 |

  Scenario: Reuse Some Previously Suggested Recipes at End of Range
    Given the following recipes are known
      | Recipe 1 | Recipe 2 | Recipe 3 |
    When the potential chef requests recipes for the date range of July 17, 2020 to July 21, 2020
    Then the following recipes should be suggested
      | recipe       | date          |
      | delicious 1  | July 20, 2020 |
      | delicious 6  | July 21, 2020 |
    And the following recipes should have been suggested in any order from July 17, 2020 to July 19, 2020
      | Recipe 1 | Recipe 2 | Recipe 3 |

  Scenario: Reuse Some Previously Suggested Recipes at Start of Range
    Given the following recipes are known
      | Recipe 1 | Recipe 2 | Recipe 3 |
    When the potential chef requests recipes for the date range of July 23, 2020 to July 27, 2020
    Then the following recipes should be suggested
      | recipe       | date          |
      | delicious 14 | July 23, 2020 |
      | repulsive 3  | July 24, 2020 |
    And the following recipes should have been suggested in any order from July 25, 2020 to July 27, 2020
      | Recipe 1 | Recipe 2 | Recipe 3 |