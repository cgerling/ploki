package org.singlebit.ploki.title

import com.android.volley.Response
import org.singlebit.ploki.wikipedia.service.SearchService

class TitleInteractor : TitleContract.Interactor {
    override var output: TitleContract.InteractorOutput? = null
    private val searchService = SearchService()

    override fun unregister() {
        output = null
    }

    override fun getFeaturedArticles() {
        searchService.getMostViewedArticles(
            8,
            Response.Listener {
                output?.onGetFeaturedArticlesSuccess(it)
            },
            Response.ErrorListener {
                it.message?.let { message ->
                    output?.onGetFeaturedArticlesError(message)
                }
            }
        )
    }

}