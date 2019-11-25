package com.clinia.widgets.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.text.ParseException
import java.util.*
import java.text.SimpleDateFormat

interface Record {
    val id: String
    val documentType: DocumentType
    val owner: String?
}

@JsonClass(generateAdapter = true)
data class Professional (
    override val id: String,
    override val documentType: DocumentType,
    override val owner: String?,
    val title: String?,
    val practiceNumber: String?,
    val professions: List<String>?,
    val name: String?,
    val phones: List<Phone>?
) : Record

@JsonClass(generateAdapter = true)
data class HealthFacility(
    override val id: String,
    override val documentType: DocumentType,
    override val owner: String?,
    val type: String?,
    val name: String?,
    val note: String?,
    val address: Address?,
    val geoPoint: GeoPoint?,
    val phones: List<Phone>?,
    val onlineBookingUrl: String?,
    val distance: Float?,
    val openingHours: OpeningHours?
) : Record {
    private fun getHours(): List<DailyHours>? =
        if (getTodayHours() == null) getDailyHours(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))
        else getTodayHours()

    private fun getTodayHours() = getDailyHours(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))

    private fun getDailyHours(day: Int): List<DailyHours>? = when (day) {
        Calendar.MONDAY -> openingHours?.monday
        Calendar.TUESDAY -> openingHours?.tuesday
        Calendar.WEDNESDAY -> openingHours?.wednesday
        Calendar.THURSDAY -> openingHours?.thursday
        Calendar.FRIDAY -> openingHours?.friday
        Calendar.SATURDAY -> openingHours?.saturday
        Calendar.SUNDAY -> openingHours?.sunday
        else -> null
    }

    private fun getCurrentInterval(hours: List<DailyHours>): DailyHours? {
        if (hours.size == 1) return  hours.first()
        else for (hour in hours)
            if (hour.isOpenAtTime(Calendar.getInstance().time))
                return hour
        return null
    }

    private fun getNextInterval(hours: List<DailyHours>): DailyHours? {
        if (hours.size == 1) return  null
        else for (hour in hours)
            if (hour.isOpenAtTime(Calendar.getInstance().time))
                return hour
        return null
    }

    /*
    Closed now
    Open 24h today
    Open now. Closes at 12:00
    Closed now. Opens at 10:00 (edited)
    Open now. 9:00–12:00, 13:00–18:00
    */
    fun getFormattedHours(): String? {
        getHours()?.let {
            if (it.isEmpty()) return "Closed now"
            else if (it[0].start == "00:00" && it[0].end == "00:00") return "Open 24h today"
            else if (getCurrentInterval(it) != null) {
                if (it.size > 1) {
                    getCurrentInterval(it)?.let { hours ->
                        return ("Open now. ${hours.start}-${hours.end}")
                    }
                } else {
                    return ("Open now. Closes at ${it.first().end}")
                }
            } else {
                getNextInterval(it)?.let { hours ->
                    return ("Closed now. Opens at ${hours.start}")
                }
            }

        }
        return null
    }
}

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
    val lat: Float,
    val lng: Float
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
) {

    fun isOpenAtTime(time: Date): Boolean {
        val calendarStart = Calendar.getInstance()
        calendarStart.time = SimpleDateFormat("H:mm", Locale.getDefault()).parse(start)!!
        calendarStart.add(Calendar.DATE, 1)

        val calendarEnd = Calendar.getInstance()
        calendarEnd.time = SimpleDateFormat("H:mm", Locale.getDefault()).parse(start)!!
        calendarEnd.add(Calendar.DATE, 1)

        return time.after(calendarStart.time) && time.before(calendarEnd.time)
    }
}

enum class DocumentType {
    @Json(name ="health_facility") HEALTH_FACILITY,
    @Json(name ="professional") PROFESSIONAL
}