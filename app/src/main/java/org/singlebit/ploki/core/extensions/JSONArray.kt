package org.singlebit.ploki.core.extensions

import org.json.JSONArray

fun <T> JSONArray.map(handler: (Any) -> T): List<T> {
    val result = mutableListOf<T>()

    for (i in 0 until length()) {
        val data = get(i)
        val item = handler(data)

        result.add(item)
    }

    return result
}