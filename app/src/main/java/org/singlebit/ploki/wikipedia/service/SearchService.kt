package org.singlebit.ploki.wikipedia.service

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import java.util.Locale
import org.json.JSONObject
import org.singlebit.ploki.wikipedia.model.SearchResultItem
import org.singlebit.ploki.wikipedia.http.HttpService
import org.singlebit.ploki.wikipedia.http.*

class SearchService(locale: Locale, context: Context) {
    private val httpService = HttpService(context)

    private val baseUrl = "https://${locale.language}.wikipedia.org/w/api.php?"
    private val searchUrl = baseUrl + query(
        mapOf(
            "action" to "query",
            "list" to "search",
            "srwhat" to "text",
            "format" to "json",
            "srinteerwiki" to true,
            "srsearch" to "{term}"
        )
    )
    private val getArticleUrl = baseUrl + query(
        mapOf("action" to "parse", "prop" to "text", "format" to "json", "pageid" to "{pageid}")
    )

    fun search(term: String, listener: Response.Listener<List<SearchResultItem>>, errorListener: Response.ErrorListener): JsonObjectRequest {
        val url =
            bindTemplateValues(
                searchUrl,
                mapOf("term" to term)
            )
        val searchListener = Response.Listener<JSONObject> {
            val response = mutableListOf<SearchResultItem>()

            val result = it.getJSONObject("query").getJSONArray("search")
            for (i in 0 until result.length()) {
                val item = result.getJSONObject(i)
                response.add(SearchResultItem.fromJSON(item))
            }

            listener.onResponse(response)
        }

        return httpService.get(url, searchListener, errorListener)
    }

    fun getArticle(id: Int, listener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener): JsonObjectRequest {
        val url =
            bindTemplateValues(
                getArticleUrl,
                mapOf("pageid" to id)
            )
        return httpService.get(url, listener, errorListener)
    }


}