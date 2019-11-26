[Widgets](../../../index.md) / [com.clinia.widgets.ui.view](../../index.md) / [SearchBar](../index.md) / [SearchBarListener](./index.md)

# SearchBarListener

`interface SearchBarListener`

Implement this interface to access various callbacks from the search bar for user interaction

onSearchBarFocusChanged(hasFocus: Boolean) is called every time the search bar gets or loses focus.
onSearchBarEnter(query: String) is called when the user presses the enter key.
onSearchBarTextChange(query: String) is called every time the user modifies the text.
onSearchBarCleared() is called when the user presses the clear button.

### Functions

| Name | Summary |
|---|---|
| [onSearchBarCleared](on-search-bar-cleared.md) | `abstract fun onSearchBarCleared(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onSearchBarEnter](on-search-bar-enter.md) | `abstract fun onSearchBarEnter(query: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onSearchBarFocusChanged](on-search-bar-focus-changed.md) | `abstract fun onSearchBarFocusChanged(hasFocus: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onSearchBarTextChange](on-search-bar-text-change.md) | `abstract fun onSearchBarTextChange(query: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
