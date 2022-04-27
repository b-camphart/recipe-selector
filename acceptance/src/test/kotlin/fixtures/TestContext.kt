package fixtures

import MessagePresenter
import Recipe
import RecipeRepository
import RecipeSuggestionPresenter
import RequestRecipes
import java.util.Date

class TestContext : MessagePresenter, RecipeSuggestionPresenter, RecipeRepository {

    private val _messages = mutableListOf<String>()
    val messages: List<String> = _messages

    override fun noRecipes() {
        _messages.add("NO RECIPES")
    }

    var suggestedRecipes: List<Pair<String, Date>>? = null
        private set
    fun suggestedRecipesOrError(): List<Pair<String, Date>> = suggestedRecipes ?: error("No suggested recipes have been received")

    override fun receiveSuggestedRecipes(recipes: Map<Date, String>, range: ClosedRange<Date>) {
        suggestedRecipes = recipes.entries.map { it.value to it.key }
    }

    var knownRecipes: List<Recipe> = listOf()

    override suspend fun listAllRecipes(): List<Recipe> = knownRecipes

    val requestRecipes = RequestRecipes(this, this, this)

}