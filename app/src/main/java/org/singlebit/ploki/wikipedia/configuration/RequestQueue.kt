package org.singlebit.ploki.wikipedia.configuration

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.android.volley.RequestQueue as VolleyRequestQueue

class RequestQueue constructor(context: Context) {
    companion object {
        @Volatile
        private var INSTANCE: RequestQueue? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RequestQueue(context).also {
                    INSTANCE = it
                }
            }
    }

    private val requestQueue: VolleyRequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> add(req: Request<T>) {
        requestQueue.add(req)
    }
}
