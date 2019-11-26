[Widgets](../../../index.md) / [com.clinia.widgets.ui.view](../../index.md) / [LocationSearchBar](../index.md) / [LocationSearchBarListener](./index.md)

# LocationSearchBarListener

`interface LocationSearchBarListener`

Implement this interface to access various callbacks from the search bar for user interaction

onLocationSearchBarFocusChanged(hasFocus: Boolean) is called every time the search bar gets or loses focus.
onLocationSearchBarEnter(location: String) is called when the user presses the enter key.
onLocationSearchBarTextChange(location: String) is called every time the user modifies the text.
onLocationSearchBarCleared() is called when the user presses the clear button.

### Functions

| Name | Summary |
|---|---|
| [onLocationSearchBarCleared](on-location-search-bar-cleared.md) | `abstract fun onLocationSearchBarCleared(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onLocationSearchBarEnter](on-location-search-bar-enter.md) | `abstract fun onLocationSearchBarEnter(location: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onLocationSearchBarFocusChanged](on-location-search-bar-focus-changed.md) | `abstract fun onLocationSearchBarFocusChanged(hasFocus: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onLocationSearchBarTextChange](on-location-search-bar-text-change.md) | `abstract fun onLocationSearchBarTextChange(location: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
