class RequestRecipes(private val messagePresenter: MessagePresenter) {
    suspend operator fun invoke() {
        messagePresenter.noRecipes()
    }
}