package org.singlebit.ploki.title

import org.singlebit.ploki.wikipedia.model.ArticleItem

class TitlePresenter(
    var view: TitleContract.View?,
    var router: TitleContract.Router?,
    var interactor: TitleContract.Interactor?
) : TitleContract.Presenter, TitleContract.InteractorOutput {
    override fun onViewCreated() {
        interactor?.getFeaturedArticles()
    }

    override fun onDestroy() {
        router = null
        interactor?.unregister()
        interactor = null
    }

    override fun onSearchIntent() {
        router?.toSearch()
    }

    override fun onArticleItemClicked(article: ArticleItem) {
        router?.toArticle(article.pageId!!)
    }

    override fun onGetFeaturedArticlesSuccess(result: List<ArticleItem>) {
        view?.publishItems(result)
    }

    override fun onGetFeaturedArticlesError(message: String) {
        view?.showError(message)
    }

}