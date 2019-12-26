package org.singlebit.ploki.wikipedia.configuration

import com.android.volley.Request
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.NoCache
import com.android.volley.RequestQueue as VolleyQueue

class RequestQueue {
    companion object {
        @Volatile
        private var INSTANCE: RequestQueue? = null
        fun getInstance() =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RequestQueue().also {
                    INSTANCE = it
                }
            }
    }

    private val requestQueue: VolleyQueue by lazy {
        val cache = NoCache()
        val network = BasicNetwork(HurlStack())


        VolleyQueue(cache, network).also {
            it.start()
        }
    }

    fun <T> add(request: Request<T>) {
        request.setShouldCache(false)
        requestQueue.add(request)
    }
}
