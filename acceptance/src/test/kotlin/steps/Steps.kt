package steps

import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue

class Steps {

    @When("the potential chef requests recipes")
    fun the_potential_chef_requests_recipes() {
        runTest {
            context.requestRecipes()
        }
    }

    @Then("a {string} message should be displayed")
    fun a_message_should_be_displayed(message: String) {
        assertTrue(context.messages.contains(message))
    }

}