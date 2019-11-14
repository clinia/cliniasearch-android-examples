package com.clinia.widgets.data.network

import com.clinia.widgets.data.PlaceSuggestion
import retrofit2.Call
import retrofit2.http.*

interface PlaceSuggestionService {
    @GET("location/v1/autocomplete?")
    fun suggest(
            @Field("input") input: String?,
            @Field("country") country: String?,
            @Field("limit") limit: Int?,
            @Field("types") place: String = "place",
            @Field("types") postCode: String = "postcode",
            @Field("types") neighborHood: String = "neighborhood",
            @Field("x-clinia-application-id") api: String = application,
            @Field("x-clinia-api-key") key: String = apiKey,
            @Header("Content-Type") auth: String = contentType
    ): Call<Array<PlaceSuggestion>>
}