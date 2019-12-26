package org.singlebit.ploki.search

import org.singlebit.ploki.wikipedia.model.ArticleItem

interface SearchContract {
    interface View {
        fun showError(message: String)
        fun publishResultItems(items: List<ArticleItem>)
    }

    interface Presenter {
        fun onDestroy()
        fun onSearchSubmit(term: String)
        fun onArticleItemClicked(article: ArticleItem)
    }

    interface Interactor {
        var output: InteractorOutput?
        fun unregister()
        fun search(term: String)
    }

    interface InteractorOutput {
        fun onSearchSuccess(result: List<ArticleItem>)
        fun onSearchError(message: String)
    }

    interface Router {
        fun toArticle(id: Int)
    }
}