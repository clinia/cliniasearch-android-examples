package com.clinia.widgets.data.network

import com.clinia.widgets.data.QuerySuggestion
import com.squareup.moshi.ToJson
import retrofit2.Call
import retrofit2.http.*
import java.net.URLEncoder

/**
 * Interface used to generate the Retrofit model for the Suggestion Clinia API.
 *
 */
interface QuerySuggestionService {
    @POST("search/v1/indexes/suggestions/query?")
    fun suggest(
            @Body body: QuerySuggestionRequest,
            @Header("Content-Type") auth: String = contentType,
            @Query("x-clinia-application-id") api: String = NetworkManager.application,
            @Query("x-clinia-api-key") key: String = NetworkManager.apiKey
    ): Call<List<QuerySuggestion>>
}

/**
 * The request body sent to get suggestions from the Query Suggestion API
 *
 * @param query The current input to be completed.
 * @param preTag The tag marking the start of the matching substring in the returned suggestions,
 * to be used for formatting. Can be an HTML tag.
 * @param postTag The tag marking the end of the matching substring in the returned suggestions,
 * to be used for formatting. Can be an HTML tag.
 * @property size       Determines how many suggestions should be returned.
 */
data class QuerySuggestionRequest(
    val query: String = "",
    val highlightPreTag: String? = null,
    val highlightPostTag: String? = null,
    val size: Int? = 5
)

/**
 * The Query suggestion request format accepted by the Clinia Query Suggestion API
 *
 * @property params URL encoded string containing the query, highlightPreTag, highlightPostTag and size
 */
data class  QuerySuggestionRequestBodyJson(
    val params: String
)

/**
 * Custom moshi adapter to create a QuerySuggestionRequestBodyJson Json Object from a QuerySuggestionRequest
 *
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