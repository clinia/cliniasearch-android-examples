[Widgets](../../index.md) / [com.clinia.widgets.data.network](../index.md) / [QuerySuggestionRequest](./index.md)

# QuerySuggestionRequest

`class QuerySuggestionRequest`

The request body sent to get suggestions from the Query Suggestion API

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The request body sent to get suggestions from the Query Suggestion API`QuerySuggestionRequest(query: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", highlightPreTag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, highlightPostTag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = 5)` |

### Properties

| Name | Summary |
|---|---|
| [highlightPostTag](highlight-post-tag.md) | The tag marking the end of the matching substring in the returned suggestions, to be used for formatting. Can be an HTML tag.`val highlightPostTag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [highlightPreTag](highlight-pre-tag.md) | The tag marking the start of the matching substring in the returned suggestions, to be used for formatting. Can be an HTML tag.`val highlightPreTag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [query](query.md) | The current input to be completed.`val query: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [size](size.md) | Determines the maximum number of suggestions that should be returned.`val size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?` |
