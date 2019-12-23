package org.singlebit.ploki.wikipedia.http

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import org.singlebit.ploki.wikipedia.configuration.RequestQueue

class HttpService(context: Context) {
    private val queue =
        RequestQueue.getInstance(context)

    fun get(url: String, listener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener): JsonObjectRequest {
        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            listener,
            errorListener
        )

        queue.add(request)

        return request
    }
}