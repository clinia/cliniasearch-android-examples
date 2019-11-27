package com.clinia.widgets.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlaceSuggestion (
    val streetAddress: String?,
    val type: String?,
    val formattedAddress: String?,
    val suite: String?,
    val postalCode: String?,
    val neighborhood: String?,
    val locality: String?,
    val place: String?,
    val district: String?,
    val region: String?,
    val regionCode: String?,
    val country: String?,
    val countryCode: String?
)