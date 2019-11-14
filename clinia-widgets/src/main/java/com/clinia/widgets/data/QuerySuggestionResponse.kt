package com.clinia.widgets.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuerySuggestion(
        val suggestion: String?,
        val facet: String?,
        val highlight: String?
)