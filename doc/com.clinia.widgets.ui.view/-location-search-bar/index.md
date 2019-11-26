[Widgets](../../index.md) / [com.clinia.widgets.ui.view](../index.md) / [LocationSearchBar](./index.md)

# LocationSearchBar

`class LocationSearchBar : LinearLayout`

The LocationSearchBar is a widget intended to be used with the CliniaViewModel
to get a location input from the user.

### Types

| Name | Summary |
|---|---|
| [LocationSearchBarListener](-location-search-bar-listener/index.md) | Implement this interface to access various callbacks from the search bar for user interaction`interface LocationSearchBarListener` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The LocationSearchBar inherits the base constructors from the LinearLayout Class`LocationSearchBar(context: Context, attrs: AttributeSet? = null, defStyleAttr: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0)` |

### Properties

| Name | Summary |
|---|---|
| [listener](listener.md) | `lateinit var listener: LocationSearchBarListener` |

### Functions

| Name | Summary |
|---|---|
| [setLocation](set-location.md) | Sets the text displayed in the search bar`fun setLocation(location: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
