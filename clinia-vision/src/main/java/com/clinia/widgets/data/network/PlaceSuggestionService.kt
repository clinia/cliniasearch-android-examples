package com.clinia.widgets.data.network

import com.clinia.widgets.data.PlaceSuggestion
import retrofit2.Call
import retrofit2.http.*

/**
 * Interface used to generate the Retrofit model for the PlaceSuggestion Clinia API.
 *
 */
interface PlaceSuggestionService {
    @GET("location/v1/autocomplete?")
    fun suggest(
            @Query("input") input: String?,
            @Query("country") country: String?,
            @Query("limit") limit: Int?,
            @Query("types") place: String = "place",
            @Query("types") postCode: String = "postcode",
            @Query("types") neighborHood: String = "neighborhood",
            @Query("x-clinia-application-id") api: String = application,
            @Query("x-clinia-api-key") key: String = apiKey,
            @Header("Content-Type") auth: String = contentType
    ): Call<List<PlaceSuggestion>>
}

/**
 * The request body sent to get suggestions from the Place Suggestion API
 *
 * @property input      The current input that requires autocompletion.
 * @property country    Limits the suggestions to a single country. (e.g. 'CA'))
 * @property size       Determines how many suggestions should be returned.
 */
data class PlaceSuggestionRequest(
    val input: String = "",
    val country: String? = null,
    val size: Int = 5
)