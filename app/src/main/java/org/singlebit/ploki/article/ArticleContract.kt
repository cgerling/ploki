package org.singlebit.ploki.article

import org.singlebit.ploki.wikipedia.model.Article

interface ArticleContract {
    interface View {
        fun showArticle(article: Article)
    }

    interface Presenter {
        fun onDestroy()
        fun onLoad(id: Int)
    }

    interface Interactor {
        var output: InteractorOutput?
        fun unregister()
        fun getArticle(id: Int)
    }

    interface InteractorOutput {
        fun onGetArticleSuccess(result: Article)
        fun onGetArticleError(message: String)
    }

    interface Router {
        fun toTitle()
    }
}