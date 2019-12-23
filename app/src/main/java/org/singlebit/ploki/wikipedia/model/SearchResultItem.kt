package org.singlebit.ploki.wikipedia.model

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class SearchResultItem(
    val ns: Int,
    val title: String,
    val pageId: Int,
    val size: Int,
    val wordcount: Int,
    val snippet: String,
    val timestamp: Date
) {
    companion object {
        @JvmStatic
        fun fromJSON(value: JSONObject): SearchResultItem {
            return SearchResultItem(
                ns = value.getInt("ns"),
                title = value.getString("title"),
                pageId = value.getInt("pageid"),
                size = value.getInt("size"),
                wordcount = value.getInt("wordcount"),
                snippet = value.getString("snippet"),
                timestamp = toDate(value.getString("timestamp"))
            )
        }

        private fun toDate(timestamp: String): Date {
            return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
                .apply {
                    timeZone = TimeZone.getTimeZone("UTC")
                }.parse(timestamp) as Date
        }
    }
}
