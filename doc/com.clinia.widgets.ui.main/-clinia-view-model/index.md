[Widgets](../../index.md) / [com.clinia.widgets.ui.main](../index.md) / [CliniaViewModel](./index.md)

# CliniaViewModel

`class CliniaViewModel : AndroidViewModel`

Used to access the data and display the data inside the ui components.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The constructor takes an Application object to be used for search using the device location.`CliniaViewModel(application: Application)` |

### Properties

| Name | Summary |
|---|---|
| [locationQuery](location-query.md) | `var locationQuery: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [query](query.md) | `var query: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| [getSearchData](get-search-data.md) | Gets the updated search data response after a search call to the API.`fun getSearchData(): LiveData<`[`SearchResponse`](../../com.clinia.widgets.data/-search-response/index.md)`>` |
| [loadMore](load-more.md) | Takes the current query and location search parameters and return the next page of results inside a LiveData variable, if there are any. If not, the liveData contains an empty array, along with the metadata associated with the search.`fun loadMore(): LiveData<`[`SearchResponse`](../../com.clinia.widgets.data/-search-response/index.md)`>` |
| [placeSuggest](place-suggest.md) | Triggers a place suggestions call.`fun placeSuggest(location: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, country: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`PlaceSuggestion`](../../com.clinia.widgets.data/-place-suggestion/index.md)`>>` |
| [querySuggest](query-suggest.md) | Triggers a query suggestions call.`fun querySuggest(query: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, preTag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, postTag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null): LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`QuerySuggestion`](../../com.clinia.widgets.data/-query-suggestion/index.md)`>>` |
| [setEnvironment](set-environment.md) | Reset the repository with the new environent variables.`fun setEnvironment(application: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, apiKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, endpoint: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
