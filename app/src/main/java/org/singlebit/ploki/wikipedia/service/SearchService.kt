package org.singlebit.ploki.wikipedia.service

import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import org.singlebit.ploki.core.constants.WIKIPEDIA_API_URL
import org.singlebit.ploki.core.extensions.map
import org.singlebit.ploki.wikipedia.http.HttpService
import org.singlebit.ploki.wikipedia.http.bindTemplateValuesOf
import org.singlebit.ploki.wikipedia.http.queryOf
import org.singlebit.ploki.wikipedia.model.ArticleItem
import org.singlebit.ploki.wikipedia.timestampToDate

class SearchService : HttpService(WIKIPEDIA_API_URL) {
    private val defaultQuery = arrayOf(
        "format" to "json",
        "action" to "query"
    )

    private val searchUrl = baseUrl + queryOf(
        *defaultQuery,
        "list" to "search",
        "srwhat" to "text",
        "srsearch" to "{term}"
    )

    private val getRandomArticlesUrl = baseUrl + queryOf(
        *defaultQuery,
        "list" to "random",
        "rnlimit" to "{count}"
    )

    private val getMostViewedArticlesUrl = baseUrl + queryOf(
        *defaultQuery,
        "list" to "mostviewed",
        "pvimlimit" to "{count}"
    )

    fun search(
        term: String,
        listener: Response.Listener<List<ArticleItem>>,
        errorListener: Response.ErrorListener
    ): JsonObjectRequest {
        val url = bindTemplateValuesOf(searchUrl, "term" to term)

        val searchListener = Response.Listener<JSONObject> {
            val response = parseSearchResponse(it)

            listener.onResponse(response)
        }

        return get(url, searchListener, errorListener)
    }

    private fun parseSearchResponse(response: JSONObject): List<ArticleItem> {
        val list = response.getJSONObject("query")
            .getJSONArray("search")

        return list.map { data ->
            val item = data as JSONObject

            ArticleItem(
                ns = item.getInt("ns"),
                title = item.getString("title"),
                pageId = item.getInt("pageid"),
                size = item.getInt("size"),
                wordcount = item.getInt("wordcount"),
                snippet = item.getString("snippet"),
                timestamp = timestampToDate(
                    item.getString(
                        "timestamp"
                    )
                )
            )
        }
    }

    fun getRandomArticles(
        count: Int = 10,
        listener: Response.Listener<List<ArticleItem>>,
        errorListener: Response.ErrorListener
    ): JsonObjectRequest {
        val url = bindTemplateValuesOf(getRandomArticlesUrl, "count" to count)

        val parseListener = Response.Listener<JSONObject> {
            val response = parseGetRandomArticlesResponse(it)
            listener.onResponse(response)
        }

        return get(url, parseListener, errorListener)
    }

    private fun parseGetRandomArticlesResponse(response: JSONObject): List<ArticleItem> {
        val list = response.getJSONObject("query").getJSONArray("random")

        return list.map { data ->
            val item = data as JSONObject

            ArticleItem(
                ns = item.getInt("ns"),
                pageId = item.getInt("id"),
                title = item.getString("title")
            )
        }
    }

    fun getMostViewedArticles(
        count: Int = 10,
        listener: Response.Listener<List<ArticleItem>>,
        errorListener: Response.ErrorListener
    ): JsonObjectRequest {
        val url = bindTemplateValuesOf(getMostViewedArticlesUrl, "count" to count)

        val parseListener = Response.Listener<JSONObject> {
            val response = parseGetMostViewedArticles(it)
            listener.onResponse(response)
        }

        return get(url, parseListener, errorListener)
    }

    private fun parseGetMostViewedArticles(response: JSONObject): List<ArticleItem> {
        val list = response.getJSONObject("query").getJSONArray("mostviewed")

        return list.map { data ->
            val item = data as JSONObject

            ArticleItem(
                ns = item.getInt("ns"),
                title = item.getString("title")
            )
        }
    }
}