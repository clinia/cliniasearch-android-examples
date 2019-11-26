package com.clinia.widgets.data.network

import com.clinia.widgets.data.PlaceSuggestion
import retrofit2.Call
import retrofit2.http.*

interface PlaceSuggestionService {
    @GET("location/v1/autocomplete?")
    fun suggest(
            @Query("input") input: String?,
            @Query("country") country: String?,
            @Query("limit") limit: Int?,
            @Query("types") types: List<String>?,
            @Query("x-clinia-application-id") api: String = application,
            @Query("x-clinia-api-key") key: String = apiKey,
            @Header("Content-Type") auth: String = contentType
    ): Call<List<PlaceSuggestion>>
}

data class PlaceSuggestionRequest(
    val input: String = "",
    val country: String? = null,
    val types: List<String>? = listOf("postcode", "place", "neighborhood"),
    val size: Int? = 5
)