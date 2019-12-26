package org.singlebit.ploki.wikipedia.http

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import org.singlebit.ploki.wikipedia.configuration.RequestQueue

open class HttpService(val baseUrl: String) {
    private val queue = RequestQueue.getInstance()

    private fun safeListener(listener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener): Response.Listener<JSONObject> {
        return Response.Listener {response ->
            val hasError = !response.isNull("error")
            if (!hasError) return@Listener listener.onResponse(response)

            response.getJSONObject("error").let {
                val message = "${it.getString("code")} ${it.getString("info")}"
                val error = VolleyError(message)

                errorListener.onErrorResponse(error)
            }
        }
    }

    fun get(url: String, listener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener): JsonObjectRequest {
        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            safeListener(listener, errorListener),
            errorListener
        )

        queue.add(request)

        return request
    }

}