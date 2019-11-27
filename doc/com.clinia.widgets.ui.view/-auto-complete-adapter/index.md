[Widgets](../../index.md) / [com.clinia.widgets.ui.view](../index.md) / [AutoCompleteAdapter](./index.md)

# AutoCompleteAdapter

`class AutoCompleteAdapter : Adapter<`[`BaseViewHolder`](../-base-view-holder/index.md)`<*>>`

The AutoCompleteAdapter is intended for use with the AutoComplete widget.
It is a custom class extending a RecyclerView  Adapter with a generic ViewHolder accepting both
QuerySuggestion and PlaceSuggestion objects.

### Types

| Name | Summary |
|---|---|
| [PlaceViewHolder](-place-view-holder/index.md) | `inner class PlaceViewHolder : `[`BaseViewHolder`](../-base-view-holder/index.md)`<`[`PlaceSuggestion`](../../com.clinia.widgets.data/-place-suggestion/index.md)`>` |
| [QueryViewHolder](-query-view-holder/index.md) | `inner class QueryViewHolder : `[`BaseViewHolder`](../-base-view-holder/index.md)`<`[`QuerySuggestion`](../../com.clinia.widgets.data/-query-suggestion/index.md)`>` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The AutoCompleteAdapter is intended for use with the AutoComplete widget. It is a custom class extending a RecyclerView  Adapter with a generic ViewHolder accepting both QuerySuggestion and PlaceSuggestion objects.`AutoCompleteAdapter(data: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>, onClick: (`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [onClick](on-click.md) | (Any) -&gt; Unit Method to be called when an item is clicked.`val onClick: (`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Functions

| Name | Summary |
|---|---|
| [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getItemViewType](get-item-view-type.md) | `fun getItemViewType(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: `[`BaseViewHolder`](../-base-view-holder/index.md)`<*>, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(viewGroup: ViewGroup, i: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`BaseViewHolder`](../-base-view-holder/index.md)`<*>` |
| [setAutoCompleteItems](set-auto-complete-items.md) | This method sets the items to display in the adapter.`fun setAutoCompleteItems(items: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
