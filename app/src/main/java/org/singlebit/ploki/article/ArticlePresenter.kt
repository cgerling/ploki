package org.singlebit.ploki.article

import org.singlebit.ploki.wikipedia.model.Article

class ArticlePresenter(
    var view: ArticleContract.View?,
    var router: ArticleContract.Router?,
    var interactor: ArticleContract.Interactor?
) : ArticleContract.Presenter, ArticleContract.InteractorOutput {

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
        router = null
    }

    override fun onLoad(id: Int) {
        interactor?.getArticle(id)
    }

    override fun onGetArticleSuccess(result: Article) {
        view?.showArticle(result)
    }

    override fun onGetArticleError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}