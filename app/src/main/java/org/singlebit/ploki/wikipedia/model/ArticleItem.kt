package org.singlebit.ploki.wikipedia.model

import java.util.Date

data class ArticleItem(
    val ns: Int,
    val title: String,
    val pageId: Int? = null,
    val size: Int? = null,
    val wordcount: Int? = null,
    val snippet: String? = null,
    val timestamp: Date? = null
)