package com.clinia.widgets.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Records(
    val id: String,
    val type: String?,
    val name: String?,
    val note: String?,
    val address: Address?,
    val geoPoint: GeoPoint?,
    val phones: List<Phone>,
    val onlineBookingUrl: String?,
    val distance: Float?,
    val openingHours: OpeningHours?
)

@JsonClass(generateAdapter = true)
data class Address(
    val streetAddress: String?,
    val postalCode: String?,
    val neighborhood: String?,
    val locality: String?,
    val place: String?,
    val region: String?,
    val regionCode: String?,
    val country: String?,
    val countryCode: String?
)

@JsonClass(generateAdapter = true)
data class GeoPoint(
    val latitude: Float,
    val longitude: Float
)

@JsonClass(generateAdapter = true)
data class Phone(
    val countryCode: String?,
    val number: String?,
    val extension: String?,
    val type: String?
)

@JsonClass(generateAdapter = true)
data class OpeningHours(
    @Json(name = "1") val monday: List<DailyHours>,
    @Json(name = "2") val tuesday: List<DailyHours>,
    @Json(name = "3") val wednesday: List<DailyHours>,
    @Json(name = "4") val thursday: List<DailyHours>,
    @Json(name = "5") val friday: List<DailyHours>,
    @Json(name = "6") val saturday: List<DailyHours>,
    @Json(name = "7") val sunday: List<DailyHours>
)

@JsonClass(generateAdapter = true)
data class DailyHours(
    val start: String,
    val end: String
)