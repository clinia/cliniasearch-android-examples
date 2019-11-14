package com.clinia.widgets.data.network

import com.clinia.widgets.data.MultiSearchResponse
import com.clinia.widgets.data.SearchResponse
import retrofit2.Call
import retrofit2.http.*
import com.squareup.moshi.ToJson
import java.net.URLEncoder

interface SearchService {
    @POST("search/v1/indexes/health_facility/query?")
    fun searchHealthFacilities(
            @Body body: SingleIndexSearchRequest,
            @Header("Content-Type") auth: String = contentType,
            @Query("x-clinia-application-id") api: String = application,
            @Query("x-clinia-api-key") key: String = apiKey
    ): Call<SearchResponse>

    @POST("search/v1/indexes/professional/query?")
    fun searchProfessionnals(
            @Body body: SingleIndexSearchRequest,
            @Header("Content-Type") auth: String = contentType,
            @Query("x-clinia-application-id") api: String = application,
            @Query("x-clinia-api-key") key: String = apiKey
    ): Call<SearchResponse>

    @POST("search/v1/indexes/*/queries?")
    fun search(
            @Body body: MultiIndexesSearchRequest,
            @Header("Content-Type") auth: String = contentType,
            @Query("x-clinia-application-id") api: String = application,
            @Query("x-clinia-api-key") key: String = apiKey
    ): Call<MultiSearchResponse>
}

data class MultiIndexesSearchRequest(
    val requests: List<SingleIndexSearchRequest>? = null
)

data class SingleIndexSearchRequest(
    val indexName: String? = null,
    val query: String? = "",
    val page: Int = 0,
    val perPage: Int = 20,
    val queryType: String? = "",
    val searchFields: List<String>? = null,
    val location: String? = null
)

data class SingleIndexSearchRequestBodyJson(
    val indexName: String? = "",
    val params: String
)

class SingleIndexAdapter {
    @ToJson
    fun toJson(s: SingleIndexSearchRequest): SingleIndexSearchRequestBodyJson {
        var params = "query=${URLEncoder.encode(s.query, "UTF-8")}&page=${s.page}&perPage=${s.perPage}"

        if (s.queryType != null) params += "&queryType=${URLEncoder.encode(s.queryType, "UTF-8")}"
        if (s.searchFields != null) params += "&searchFields=${URLEncoder.encode(s.searchFields.toString(), "UTF-8")}"
        if (s.location != null) params += "&location=${URLEncoder.encode(s.location, "UTF-8")}"

        return SingleIndexSearchRequestBodyJson(
            s.indexName,
            params
        )
    }
}