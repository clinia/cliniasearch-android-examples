[Widgets](../../index.md) / [com.clinia.widgets.ui.main](../index.md) / [CliniaViewModel](index.md) / [loadMore](./load-more.md)

# loadMore

`fun loadMore(): LiveData<`[`SearchResponse`](../../com.clinia.widgets.data/-search-response/index.md)`>`

Takes the current query and location search parameters and return the next page
of results inside a LiveData variable, if there are any.
If not, the liveData contains an empty array, along with the metadata associated with the search.

**Return**
a LiveData containing a SearchResponse data object

