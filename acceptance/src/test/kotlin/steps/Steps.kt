package steps

import Recipe
import io.cucumber.datatable.DataTable
import io.cucumber.java.PendingException
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import java.util.*
import kotlin.time.Duration.Companion.days

class Steps {

    @Given("the following recipes are known")
    fun the_following_recipes_are_known(data: DataTable) {
        context.knownRecipes = data.asLists().flatten().map { name ->
            object : Recipe() {}
        }

    }

    @When("the potential chef requests recipes for a date range spanning {int} days")
    fun the_potential_chef_requests_recipes_for_a_date_range_spanning_number_days(dayCount: Int) {
        val today = Date()
        val range = today .. Date(today.time + dayCount.days.inWholeMilliseconds)
        runTest {
            context.requestRecipes(range)
        }
    }

    @When("the potential chef requests recipes")
    fun the_potential_chef_requests_recipes() {
        the_potential_chef_requests_recipes_for_a_date_range_spanning_number_days(1)
    }

    @Then("a {string} message should be displayed")
    fun a_message_should_be_displayed(message: String) {
        assertTrue(context.messages.contains(message))
    }

    @Then("the following recipes should have been suggested an approximate number of times")
    fun the_following_recipes_should_have_been_suggested_an_approximate_number_of_times(data: DataTable) {
        data.asMaps().forEach { row ->
            val recipe = row.getValue("recipe")
            val approxCount = row.getValue("approximate count").toInt()
            val margin = row.getValue("margin").toInt()
            val expectedRange = approxCount - margin .. approxCount + margin
            val actualCount = context.suggestedRecipesOrError().count { it.first == recipe }
            assertTrue(expectedRange.contains(actualCount)) {
                "Expected $recipe to be suggested within $expectedRange.  Found $actualCount"
            }
        }
    }

    @Then("the following recipes should have been suggested in any order")
    fun the_following_recipes_should_have_been_suggested_in_any_order(data: DataTable) {
        val suggestedRecipes = context.suggestedRecipesOrError()
        data.asLists().flatten().forEach { recipeName ->
            assertNotNull(suggestedRecipes.find { it.first == recipeName }) {
                "Did not find $recipeName in suggested recipes"
            }
        }
    }

}