[Widgets](../../index.md) / [com.clinia.widgets.ui.view](../index.md) / [ResultAdapter](./index.md)

# ResultAdapter

`class ResultAdapter : Adapter<ViewHolder>`

The ResultAdapter class in intended for use with the ResultsList widget to display search results
to the user and allow user interaction.

### Types

| Name | Summary |
|---|---|
| [ViewHolder](-view-holder/index.md) | `inner class ViewHolder : ViewHolder` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The ResultAdapter class in intended for use with the ResultsList widget to display search results to the user and allow user interaction.`ResultAdapter(context: Context, data: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`HealthFacility`](../../com.clinia.widgets.data/-health-facility/index.md)`>, onClick: ((`[`HealthFacility`](../../com.clinia.widgets.data/-health-facility/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)? = null)` |

### Functions

| Name | Summary |
|---|---|
| [addData](add-data.md) | Add the data passed in parameter to the data currently displayed.`fun addData(mutableList: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`HealthFacility`](../../com.clinia.widgets.data/-health-facility/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getIndex](get-index.md) | Return the index of a given Result based on its ID`fun getIndex(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: ViewHolder, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: ViewGroup, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): ViewHolder` |
| [setData](set-data.md) | Replace the currently displayed results by the ones passed in parameters.`fun setData(mutableList: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`HealthFacility`](../../com.clinia.widgets.data/-health-facility/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
