package org.singlebit.ploki.search

import com.android.volley.Response
import org.singlebit.ploki.wikipedia.service.ArticleService
import org.singlebit.ploki.wikipedia.service.SearchService

class SearchInteractor : SearchContract.Interactor {
    override var output: SearchContract.InteractorOutput? = null
    private val searchService = SearchService()

    override fun unregister() {
        output = null
    }

    override fun search(term: String) {
        searchService.search(
            term,
            Response.Listener {
                output?.onSearchSuccess(it)
            },
            Response.ErrorListener {
                it.message?.let { message ->
                    output?.onSearchError(message)
                }
            }
        )
    }

}