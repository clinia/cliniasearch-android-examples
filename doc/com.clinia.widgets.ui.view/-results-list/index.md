[Widgets](../../index.md) / [com.clinia.widgets.ui.view](../index.md) / [ResultsList](./index.md)

# ResultsList

`class ResultsList : RecyclerView`

The ResultsList widget is intended for use with the ResultsAdapter and CliniaViewModel classes.

It displays the HealthFacility results using Material Design cards and displays their name,
address,

### Types

| Name | Summary |
|---|---|
| [OnLoadMoreListener](-on-load-more-listener/index.md) | Implement this interface to set the method called when the user scrolls to the bottom of the results list.`interface OnLoadMoreListener` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | ResultsList inherits the base constructors from the RecyclerViewClass`ResultsList(context: Context, attrs: AttributeSet? = null, defStyleAttr: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0)` |

### Properties

| Name | Summary |
|---|---|
| [listener](listener.md) | `lateinit var listener: OnLoadMoreListener` |

### Functions

| Name | Summary |
|---|---|
| [getResult](get-result.md) | `fun getResult(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
