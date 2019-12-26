package org.singlebit.ploki.wikipedia.service

import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import org.singlebit.ploki.core.constants.WIKIPEDIA_API_URL
import org.singlebit.ploki.wikipedia.http.HttpService
import org.singlebit.ploki.wikipedia.http.*
import org.singlebit.ploki.wikipedia.model.Article

class ArticleService : HttpService(WIKIPEDIA_API_URL) {
    private val format =  "format" to "json"

    private val getArticleUrl = baseUrl + queryOf(
        format,
        "action" to "parse",
        "prop" to "text",
        "mobileformat" to true,
        "pageid" to "{pageid}"
    )

    fun getArticle(
        id: Int,
        listener: Response.Listener<Article>,
        errorListener: Response.ErrorListener
    ): JsonObjectRequest {
        val url = bindTemplateValuesOf(getArticleUrl, "pageid" to id)

        val articleListener = Response.Listener<JSONObject> {
            val response = parseGetArticleResponse(it)

            listener.onResponse(response)
        }

        return get(url, articleListener, errorListener)
    }

    private fun parseGetArticleResponse(response: JSONObject): Article {
        val item = response.getJSONObject("parse")

        return Article(
            id = item.getInt("pageid"),
            title = item.getString("title"),
            content = item.getJSONObject("text").getString("*")
        )
    }

}