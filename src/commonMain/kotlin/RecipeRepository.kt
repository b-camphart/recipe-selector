interface RecipeRepository {
    suspend fun listAllRecipes(): List<Recipe>
}