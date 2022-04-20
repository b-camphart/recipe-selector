Feature: Avoid Reusing Recipes

  Scenario: No Recipes are Known
    When the potential chef requests recipes
    Then a "NO RECIPES" message should be displayed

  Scenario: Randomly Suggest New Recipes
    Given the following recipes are known
      | Recipe 1 | Recipe 2 | Recipe 3 | Recipe 4 |
    When the potential chef requests recipes for a date range spanning 1000 days
    Then the following recipes should have been suggested an approximate number of times
      | recipe   | approximate count | margin |
      | Recipe 1 | 250               | 75     |
      | Recipe 2 | 250               | 75     |
      | Recipe 3 | 250               | 75     |
      | Recipe 4 | 250               | 75     |

  Scenario: Should Avoid Reusing Recipes
    Given the following recipes are known
      | Recipe 1 | Recipe 2 | Recipe 3 | Recipe 4 |
    When the potential chef requests recipes for a date range spanning 4 days
    Then the following recipes should have been suggested in any order
      | Recipe 1 | Recipe 2 | Recipe 3 | Recipe 4 |