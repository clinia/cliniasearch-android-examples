[Widgets](../../index.md) / [com.clinia.widgets.data.network](../index.md) / [PlaceSuggestionRequest](./index.md)

# PlaceSuggestionRequest

`data class PlaceSuggestionRequest`

The request body sent to get suggestions from the Place Suggestion API

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The request body sent to get suggestions from the Place Suggestion API`PlaceSuggestionRequest(input: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", country: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 5)` |

### Properties

| Name | Summary |
|---|---|
| [country](country.md) | Limits the suggestions to a single country. (e.g. 'CA'))`val country: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [input](input.md) | The current input that requires autocompletion.`val input: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [size](size.md) | Determines the maximum number of suggestions that should be returned.`val size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
