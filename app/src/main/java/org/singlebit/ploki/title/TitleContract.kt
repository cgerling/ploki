package org.singlebit.ploki.title

import org.singlebit.ploki.wikipedia.model.ArticleItem

interface TitleContract {
    interface View {
        fun showError(message: String)
        fun publishItems(items: List<ArticleItem>)
    }

    interface Presenter {
        fun onViewCreated()
        fun onDestroy()
        fun onSearchIntent()
        fun onArticleItemClicked(article: ArticleItem)
    }

    interface Interactor {
        var output: InteractorOutput?
        fun unregister()
        fun getFeaturedArticles()
    }

    interface InteractorOutput {
        fun onGetFeaturedArticlesSuccess(result: List<ArticleItem>)
        fun onGetFeaturedArticlesError(message: String)
    }

    interface Router {
        fun toSearch()
        fun toArticle(id: Int)
    }
}