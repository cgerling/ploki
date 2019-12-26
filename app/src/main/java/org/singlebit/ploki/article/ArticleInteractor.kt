package org.singlebit.ploki.article

import com.android.volley.Response
import org.singlebit.ploki.wikipedia.service.ArticleService

class ArticleInteractor : ArticleContract.Interactor {
    override var output: ArticleContract.InteractorOutput? = null
    private val articleService = ArticleService()

    override fun unregister() {
        output = null
    }

    override fun getArticle(id: Int) {
        articleService.getArticle(
            id,
            Response.Listener {
                output?.onGetArticleSuccess(it)
            },
            Response.ErrorListener {
                it.message?.let { message ->
                    output?.onGetArticleError(message)
                }
            })
    }

}