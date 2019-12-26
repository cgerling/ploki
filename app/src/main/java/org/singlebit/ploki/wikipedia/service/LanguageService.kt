package org.singlebit.ploki.wikipedia.service

import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import org.singlebit.ploki.core.constants.WIKIPEDIA_URL
import org.singlebit.ploki.core.extensions.map
import org.singlebit.ploki.wikipedia.http.HttpService
import org.singlebit.ploki.wikipedia.http.*
import org.singlebit.ploki.wikipedia.model.Language

class LanguageService : HttpService(WIKIPEDIA_URL) {
    private val getLanguagesUrl = baseUrl + queryOf(
        "action" to "query",
        "meta" to "siteinfo",
        "siprop" to "languages",
        "format" to "json"
    )

    fun getLanguages(
        id: Int,
        listener: Response.Listener<List<Language>>,
        errorListener: Response.ErrorListener
    ): JsonObjectRequest {
        val url = bindTemplateValuesOf(getLanguagesUrl, "pageid" to id)

        val articleListener = Response.Listener<JSONObject> {
            val response = parseGetLanguageResponse(it)

            listener.onResponse(response)
        }

        return get(url, articleListener, errorListener)
    }

    private fun parseGetLanguageResponse(response: JSONObject): List<Language> {
        val list = response.getJSONObject("query")
            .getJSONArray("languages")

        return list.map { data ->
            val item = data as JSONObject

            fromJSON(item)
        }
    }

    private fun fromJSON(data: JSONObject): Language {
        return Language(
            code = data.getString("code"),
            bcp47 = data.getString("bcp47"),
            label = data.getString("*")
        )
    }
}