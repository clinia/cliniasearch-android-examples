package com.clinia.widgets.data.network

import com.clinia.widgets.data.QuerySuggestResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.Header
import retrofit2.http.POST

interface QuerySuggestService {

    @POST("search/v1/queries?")
    fun querySuggest(
        @Body querySuggestRequestBody: QuerySuggestRequestBody,
        @Header("Content-Type") auth: String = contentType,
        @Header("Authorization") content: String = token,
        @Field("locale") locale: String = "en",
        @Field("x-clinia-application-id") applicationId: String = "TODO",
        @Field("x-clinia-api-key") apiKey: String = "test",
        @Field("x-clinia-agent") agent: String = "Clinia%20for%20JavaScript%20(1.0.0-beta.1)%3B%20Browser"
    ): Call<QuerySuggestResponse>
}

data class QuerySuggestRequestBody(
    val query: String? = "",
    val highlightPreTag: String? = null,
    val highlightPostTag: String? = null,
    var size: Int = 5
)