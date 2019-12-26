package org.singlebit.ploki.wikipedia.http

fun bindTemplateValuesOf(template: String, vararg values: Pair<String, Any>): String {
    var url = template
    for (entry in values) {
        val key = "{${entry.first}}"
        url = url.replace(key, entry.second.toString())
    }

    return url
}

fun queryOf(vararg params: Pair<String, Any>): String {
    return params.map { "${it.first}=${it.second}" }.joinToString("&")
}
