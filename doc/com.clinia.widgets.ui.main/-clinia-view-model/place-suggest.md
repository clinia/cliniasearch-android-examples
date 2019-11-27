[Widgets](../../index.md) / [com.clinia.widgets.ui.main](../index.md) / [CliniaViewModel](index.md) / [placeSuggest](./place-suggest.md)

# placeSuggest

`fun placeSuggest(location: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, country: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`PlaceSuggestion`](../../com.clinia.widgets.data/-place-suggestion/index.md)`>>`

Triggers a place suggestions call.

### Parameters

`location` - The current location input to be completed.

`country` - This parameters restrains the suggestions to a single country. Default value is null, for global results.

**Return**
LiveData containing a list of PlaceSuggestion objects

