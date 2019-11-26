package com.clinia.widgets.data.network

import com.clinia.widgets.data.QuerySuggestion
import com.squareup.moshi.ToJson
import retrofit2.Call
import retrofit2.http.*
import java.net.URLEncoder

/**
 * @suppress
 */
interface QuerySuggestionService {
    @POST("search/v1/indexes/suggestions/query?")
    fun suggest(
            @Body body: QuerySuggestionRequest,
            @Header("Content-Type") content: String = contentType,
            @Query("x-clinia-application-id") appId: String = NetworkManager.application,
            @Query("x-clinia-api-key") apiKey: String = NetworkManager.apiKey
    ): Call<List<QuerySuggestion>>
}

/**
 * The request body sent to get suggestions from the Query Suggestion API
 *
 * @property query The current input to be completed.
 * @property highlightPreTag The tag marking the start of the matching substring in the returned suggestions,
 * to be used for formatting. Can be an HTML tag.
 * @property highlightPostTag The tag marking the end of the matching substring in the returned suggestions,
 * to be used for formatting. Can be an HTML tag.
 * @property size Determines the maximum number of suggestions that should be returned.
 */
class QuerySuggestionRequest(
    val query: String = "",
    val highlightPreTag: String? = null,
    val highlightPostTag: String? = null,
    val size: Int? = 5
)

/**
 * @suppress
 */
data class  QuerySuggestionRequestBodyJson(
    val params: String
)

/**
 * @suppress
 */
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