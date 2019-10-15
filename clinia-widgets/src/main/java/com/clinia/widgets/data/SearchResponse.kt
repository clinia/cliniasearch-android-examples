package com.clinia.widgets.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    val records: MutableList<Records>,
    val info: SearchInfo
)

@JsonClass(generateAdapter = true)
data class SearchInfo(
    val query: String,
    val currentPage: Int,
    val numPages: Int,
    val perPage: Int,
    val total: Int,
    val geo: GeoInfo?
)

@JsonClass(generateAdapter = true)
data class GeoInfo (
    val canonicalName: String,
    val type: String,
    val location: GeoPointSearch
)

@JsonClass(generateAdapter = true)
data class GeoPointSearch (
    val lat: String,
    val lng: String
)

