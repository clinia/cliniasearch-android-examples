[Widgets](../../index.md) / [com.clinia.widgets.data](../index.md) / [SearchDataRepository](./index.md)

# SearchDataRepository

`class SearchDataRepository`

Repository through which to access Clinia's APIs.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Repository through which to access Clinia's APIs.`SearchDataRepository(endPoint: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, appId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, apiKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)` |

### Functions

| Name | Summary |
|---|---|
| [getPlaceSuggestions](get-place-suggestions.md) | Get places suggestions based on an input`fun getPlaceSuggestions(request: `[`PlaceSuggestionRequest`](../../com.clinia.widgets.data.network/-place-suggestion-request/index.md)`): MutableLiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`PlaceSuggestion`](../-place-suggestion/index.md)`>>` |
| [getQuerySuggestions](get-query-suggestions.md) | Get query suggestions based on an input.`fun getQuerySuggestions(request: `[`QuerySuggestionRequest`](../../com.clinia.widgets.data.network/-query-suggestion-request/index.md)`): MutableLiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`QuerySuggestion`](../-query-suggestion/index.md)`>>` |
| [search](search.md) | Search in many indexes at a time.`fun search(request: `[`MultiIndexesSearchRequest`](../../com.clinia.widgets.data.network/-multi-indexes-search-request/index.md)`): MutableLiveData<`[`MultiSearchResponse`](../-multi-search-response/index.md)`>` |
| [searchHealthFacilities](search-health-facilities.md) | Search exclusively inside the `Health Facility` index.`fun searchHealthFacilities(request: `[`SingleIndexSearchRequest`](../../com.clinia.widgets.data.network/-single-index-search-request/index.md)`): MutableLiveData<`[`SearchResponse`](../-search-response/index.md)`>` |
| [searchProfessionals](search-professionals.md) | Search exclusively inside the `Professionals` index.`fun searchProfessionals(request: `[`SingleIndexSearchRequest`](../../com.clinia.widgets.data.network/-single-index-search-request/index.md)`): MutableLiveData<`[`SearchResponse`](../-search-response/index.md)`>` |
