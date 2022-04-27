import java.util.Date

class RequestRecipes(
    private val messagePresenter: MessagePresenter,
    private val recipeSuggestionPresenter: RecipeSuggestionPresenter,
    private val recipes: RecipeRepository
) {
    suspend operator fun invoke(inRange: ClosedRange<Date>) {
        messagePresenter.noRecipes()
    }
}