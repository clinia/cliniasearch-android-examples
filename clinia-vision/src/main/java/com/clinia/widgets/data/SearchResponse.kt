package com.clinia.widgets.data

import com.clinia.widgets.data.network.SingleIndexSearchRequest
import com.clinia.widgets.data.network.SingleIndexSearchRequestBodyJson
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson
import java.net.URLEncoder

@JsonClass(generateAdapter = true)
data class MultiSearchResponse(
    val results: List<SearchResponse>
)

@JsonClass(generateAdapter = true)
data class SearchResponse(
    val indexName: String?,
    val records: MutableList<Record>,
    val meta: Metadata
)

/**
 * @suppress
 */
data class RecordJson(
    val id: String,
    val documentType: DocumentType,
    val owner: String?,
    val type: String? = null,
    val name: String? = null,
    val note: String? = null,
    val address: Address? = null,
    val geoPoint: GeoPoint? = null,
    val phones: List<Phone>? = null,
    val onlineBookingUrl: String? = null,
    val distance: Float? = null,
    val openingHours: OpeningHours? = null,
    val title: String? = null,
    val practiceNumber: String? = null,
    val professions: List<String>? = null
)

/**
 * @suppress
 */
class RecordAdapter {
    @ToJson
    fun toJson(r: Record): RecordJson {
        return RecordJson(r.id, r.documentType, r.owner)
    }

    @FromJson
    fun fromJson(r: RecordJson): Record? {
        var record: Record? = null
        when(r.documentType){
            DocumentType.HEALTH_FACILITY -> record = HealthFacility(
                    r.id,
                    r.documentType,
                    r.owner,
                    r.type,
                    r.name,
                    r.note,
                    r.address,
                    r.geoPoint,
                    r.phones,
                    r.onlineBookingUrl,
                    r.distance,
                    r.openingHours
            )
            DocumentType.PROFESSIONAL -> record = Professional(
                    r.id,
                    r.documentType,
                    r.owner,
                    r.title,
                    r.practiceNumber,
                    r.professions,
                    r.name,
                    r.phones
            )
            else -> {
                record = null
            }
        }

        return record
    }
}

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

