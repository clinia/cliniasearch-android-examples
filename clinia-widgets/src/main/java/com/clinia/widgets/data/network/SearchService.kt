package com.clinia.widgets.data.network

import com.clinia.widgets.data.MultiSearchResponse
import com.clinia.widgets.data.SearchResponse
import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import retrofit2.Call
import retrofit2.http.*

interface SearchService {
    @POST("search/v1/indexes/health_facility/query?")
    fun searchHealthFacilities(
            @Body body: SingleIndexSearchRequestBody,
            @Header("Content-Type") auth: String = contentType,
            @Field("x-clinia-application-id") api: String = application,
            @Field("x-clinia-api-key") key: String = apiKey
    ): Call<SearchResponse>

    @POST("search/v1/indexes/professional/query?")
    fun searchProfessionnals(
            @Body body: SingleIndexSearchRequestBody,
            @Header("Content-Type") auth: String = contentType,
            @Field("x-clinia-application-id") api: String = application,
            @Field("x-clinia-api-key") key: String = apiKey
    ): Call<SearchResponse>

    @POST("search/v1/indexes/*/queries?")
    fun search(
            @Body body: MultiIndexesSearchRequestBody,
            @Header("Content-Type") auth: String = contentType,
            @Field("x-clinia-application-id") api: String = application,
            @Field("x-clinia-api-key") key: String = apiKey
    ): Call<MultiSearchResponse>
}

data class MultiIndexesSearchRequestBody(
    val requests: Array<SingleIndexSearchRequestBody>? = null
)

data class SingleIndexSearchRequestBody(
    val indexName: String? = null,

//    val params: String? = null
    val query: String? = "",
    val page: Int = 0,
    val perPage: Int = 20,
    val queryType: String? = "",
    val searchFields: Array<String>? = null,
    val location: String? = null
)