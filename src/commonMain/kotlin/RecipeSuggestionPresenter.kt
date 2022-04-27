import java.util.*

interface RecipeSuggestionPresenter {
    fun receiveSuggestedRecipes(recipes: Map<Date, String>, range: ClosedRange<Date>)
}