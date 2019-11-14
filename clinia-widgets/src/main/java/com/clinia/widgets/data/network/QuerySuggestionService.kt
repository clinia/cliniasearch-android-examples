package com.clinia.widgets.data.network

import com.clinia.widgets.data.QuerySuggestion
import com.squareup.moshi.ToJson
import retrofit2.Call
import retrofit2.http.*
import java.net.URLEncoder

interface QuerySuggestionService {
    @POST("search/v1/indexes/suggestions/query?")
    fun suggest(
            @Body body: QuerySuggestionRequest,
            @Header("Content-Type") auth: String = contentType,
            @Query("x-clinia-application-id") api: String = application,
            @Query("x-clinia-api-key") key: String = apiKey
    ): Call<Array<QuerySuggestion>>
}

data class QuerySuggestionRequest(
    val query: String = "",
    val highlightPreTag: String? = null,
    val highlightPostTag: String? = null,
    val size: Int? = 5
)

data class  QuerySuggestionRequestBodyJson(
    val params: String
)

class QuerySuggestionAdapter {
    @ToJson
    fun toJson(q: QuerySuggestionRequest) : QuerySuggestionRequestBodyJson{
        var params = "query=${URLEncoder.encode(q.query, "UTF-8")}"

        if (q.highlightPreTag != null) params += "&highlightPreTag=${URLEncoder.encode(q.highlightPreTag, "UTF-8")}"
        if (q.highlightPostTag != null) params += "&highlightPostTag=${URLEncoder.encode(q.highlightPostTag, "UTF-8")}"
        if (q.size != null) params += "&size=${q.size}"

        return QuerySuggestionRequestBodyJson(params)
    }
}