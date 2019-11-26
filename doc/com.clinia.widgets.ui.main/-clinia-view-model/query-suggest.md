[Widgets](../../index.md) / [com.clinia.widgets.ui.main](../index.md) / [CliniaViewModel](index.md) / [querySuggest](./query-suggest.md)

# querySuggest

`fun querySuggest(query: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, preTag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, postTag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null): LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`QuerySuggestion`](../../com.clinia.widgets.data/-query-suggestion/index.md)`>>`

Triggers a query suggestions call.

### Parameters

`query` - The current input to be completed.

`preTag` - The tag marking the start of the matching substring in the returned suggestions,
to be used for formatting. Can be an HTML tag.

`postTag` - The tag marking the end of the matching substring in the returned suggestions,
to be used for formatting. Can be an HTML tag.

**Return**
LiveData containing a list of QuerySuggestion objects

