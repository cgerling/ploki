package org.singlebit.ploki.wikipedia

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun timestampToDate(timestamp: String): Date {
    return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        .apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        .parse(timestamp) as Date
}