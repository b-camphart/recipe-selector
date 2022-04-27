package fixtures

import MessagePresenter

class TestContext : MessagePresenter {

    private val _messages = mutableListOf<String>()
    val messages: List<String> = _messages

    override fun noRecipes() {
        _messages.add("NO RECIPES")
    }

}