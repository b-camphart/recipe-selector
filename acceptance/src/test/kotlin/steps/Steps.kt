package steps

import io.cucumber.java.en.Then
import org.junit.jupiter.api.Assertions.assertTrue

class Steps {

    @Then("a {string} message should be displayed")
    fun a_message_should_be_displayed(message: String) {
        assertTrue(context.messages.contains(message))
    }

}