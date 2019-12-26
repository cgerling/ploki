package org.singlebit.ploki.search

import org.singlebit.ploki.wikipedia.model.ArticleItem

class SearchPresenter(
    var view: SearchContract.View?,
    var router: SearchContract.Router?,
    var interactor: SearchContract.Interactor?
) : SearchContract.Presenter, SearchContract.InteractorOutput {

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
        router = null
    }

    override fun onSearchSubmit(term: String) {
        interactor?.search(term)
    }

    override fun onArticleItemClicked(article: ArticleItem) {
        router?.toArticle(article.pageId!!)
    }

    override fun onSearchSuccess(result: List<ArticleItem>) {
        view?.publishResultItems(result)
    }

    override fun onSearchError(message: String) {
        view?.showError(message)
    }

}