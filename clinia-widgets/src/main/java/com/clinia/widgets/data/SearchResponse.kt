package com.clinia.widgets.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MultiSearchResponse(
    val results: Array<SearchResponse>
)

@JsonClass(generateAdapter = true)
data class SearchResponse(
    val indexName: String?,
    val records: MutableList<Records>,
    val meta: Metadata
)

@JsonClass(generateAdapter = true)
data class Metadata(
    val query: String?,
    val page: Int?,
    val numPages: Int?,
    val perPage: Int?,
    val total: Int?,
    val aroundLatLng: String?,
    val insideBoundingBox: String?
)

@JsonClass(generateAdapter = true)
data class GeoPointSearch (
    val lat: String,
    val lng: String
)

