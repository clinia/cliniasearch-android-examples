[Widgets](../../index.md) / [com.clinia.widgets.data](../index.md) / [HealthFacility](./index.md)

# HealthFacility

`@JsonClass(true) data class HealthFacility : `[`Record`](../-record/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `HealthFacility(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, documentType: `[`DocumentType`](../-document-type/index.md)`, owner: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, note: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, address: `[`Address`](../-address/index.md)`?, geoPoint: `[`GeoPoint`](../-geo-point/index.md)`?, phones: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Phone`](../-phone/index.md)`>?, onlineBookingUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, distance: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)`?, openingHours: `[`OpeningHours`](../-opening-hours/index.md)`?)` |

### Properties

| Name | Summary |
|---|---|
| [address](address.md) | `val address: `[`Address`](../-address/index.md)`?` |
| [distance](distance.md) | `val distance: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)`?` |
| [documentType](document-type.md) | `val documentType: `[`DocumentType`](../-document-type/index.md) |
| [geoPoint](geo-point.md) | `val geoPoint: `[`GeoPoint`](../-geo-point/index.md)`?` |
| [id](id.md) | `val id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [name](name.md) | `val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [note](note.md) | `val note: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [onlineBookingUrl](online-booking-url.md) | `val onlineBookingUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [openingHours](opening-hours.md) | `val openingHours: `[`OpeningHours`](../-opening-hours/index.md)`?` |
| [owner](owner.md) | `val owner: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [phones](phones.md) | `val phones: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Phone`](../-phone/index.md)`>?` |
| [type](type.md) | `val type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |

### Functions

| Name | Summary |
|---|---|
| [getFormattedHours](get-formatted-hours.md) | `fun getFormattedHours(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
