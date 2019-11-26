package com.clinia.widgets.data.network

import com.clinia.widgets.data.MultiSearchResponse
import com.clinia.widgets.data.SearchResponse
import retrofit2.Call
import retrofit2.http.*
import com.squareup.moshi.ToJson
import java.net.URLEncoder

/**
 * @suppress
 */
interface SearchService {
    @POST("search/v1/indexes/health_facility/query?")
    fun searchHealthFacilities(
            @Body body: SingleIndexSearchRequest,
            @Header("Content-Type") auth: String = contentType,
            @Query("x-clinia-application-id") api: String = NetworkManager.application,
            @Query("x-clinia-api-key") key: String = NetworkManager.apiKey
    ): Call<SearchResponse>

    @POST("search/v1/indexes/professional/query?")
    fun searchProfessionnals(
            @Body body: SingleIndexSearchRequest,
            @Header("Content-Type") auth: String = contentType,
            @Query("x-clinia-application-id") api: String = NetworkManager.application,
            @Query("x-clinia-api-key") key: String = NetworkManager.apiKey
    ): Call<SearchResponse>

    @POST("search/v1/indexes/*/queries?")
    fun search(
            @Body body: MultiIndexesSearchRequest,
            @Header("Content-Type") auth: String = contentType,
            @Query("x-clinia-application-id") api: String = NetworkManager.application,
            @Query("x-clinia-api-key") key: String = NetworkManager.apiKey
    ): Call<MultiSearchResponse>
}

/**
 *  The request body sent to make a Multiple Index Search from the Clinia Search API
 *
 * @property requests A list of Single index Searches that will be processed individually by the API
 */
data class MultiIndexesSearchRequest(
    val requests: List<SingleIndexSearchRequest>? = null
)

/**
 *  The request body sent to make a Single Index Search from the Clinia Search API
 *
 * @property indexName The index targeted by this search.
 * @property query
 * @property page
 * @property perPage
 * @property queryType
 * @property searchFields
 * @property location
 */
data class SingleIndexSearchRequest(
    val indexName: String? = null,
    val query: String? = "",
    val page: Int = 0,
    val perPage: Int = 20,
    val queryType: String? = "",
    val searchFields: List<String>? = null,
    val location: String? = null
)

/**
 * @suppress
 */
data class SingleIndexSearchRequestBodyJson(
    val indexName: String? = "",
    val params: String
)

/**
 * @suppress
 */
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