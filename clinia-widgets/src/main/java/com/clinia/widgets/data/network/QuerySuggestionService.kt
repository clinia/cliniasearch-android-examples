package com.clinia.widgets.data.network

import com.clinia.widgets.data.QuerySuggestion
import retrofit2.Call
import retrofit2.http.*

interface QuerySuggestionService {
    @POST("search/v1/indexes/suggestions/query?")
    fun suggest(
            @Body body: QuerySuggestionRequestBody,
            @Header("Content-Type") auth: String = contentType,
            @Field("x-clinia-application-id") api: String = application,
            @Field("x-clinia-api-key") key: String = apiKey
    ): Call<Array<QuerySuggestion>>
}

data class QuerySuggestionRequestBody(
//        val params: String? = null
    val query: String = "",
    val highlightPreTag: String? = null,
    val highlightPostTag: String? = null,
    val size: Int? = 5
)