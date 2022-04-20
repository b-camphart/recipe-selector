Feature: Select_Recipes_with_Similar_Ingredients

  Background:
    Given the following recipes have these ingredients
      | recipe   | ingredient list   |
      | Recipe 1 | chicken, broccoli |
      | Recipe 2 | pork, carrots     |
      | Recipe 3 | chicken, peas     |
      | Recipe 4 | beef, broccoli    |

  Scenario: Prefer Recipes with Similar Ingredients
    Given The following recipes have already been planned
      | recipe   | date          |
      | Recipe 3 | July 20, 2020 |
    When the potential chef requests recipes for the date range of July 20, 2020 to July 22, 2020
    Then the following recipes should be suggested
      | recipe   | date          |
      | Recipe 3 | July 20, 2020 |
    And the following recipes should have been suggested in any order from July 21, 2020 to July 22, 2020
      | Recipe 1 | Recipe 4 |

  Scenario: Prefer Unique Recipes over Recipes with Similar Ingredients
    Given The following recipes have already been planned
      | recipe   | date          |
      | Recipe 3 | July 20, 2020 |
    When the potential chef requests recipes for the date range of July 20, 2020 to July 23, 2020
    Then the following recipes should be suggested
      | recipe   | date          |
      | Recipe 3 | July 20, 2020 |
    And the following recipes should have been suggested in any order from July 21, 2020 to July 23, 2020
      | Recipe 1 | Recipe 2 | Recipe 4 |

