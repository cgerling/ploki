package org.singlebit.ploki.wikipedia.http

fun bindTemplateValues(template: String, values: Map<String, Any>): String {
    var url = template
    for (entry in values.entries) {
        val key = "{${entry.key}}"
        url = url.replace(key, entry.value.toString())
    }

    return url
}

fun query(params: Map<String, Any>): String {
    return params.map { "${it.key}=${it.value}" }.joinToString("&")
}

