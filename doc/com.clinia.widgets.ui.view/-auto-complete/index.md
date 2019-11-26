[Widgets](../../index.md) / [com.clinia.widgets.ui.view](../index.md) / [AutoComplete](./index.md)

# AutoComplete

`class AutoComplete : LinearLayout`

The AutoComplete Widget to be used with the CliniaViewModel to display query and location
suggestions to the user.

### Types

| Name | Summary |
|---|---|
| [AutoCompleteListener](-auto-complete-listener/index.md) | Implement this interface to intercept click events on the AutoComplete items`interface AutoCompleteListener` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The Autocomplete widget constructor inherits every default value from the LinearLayout constructor.`AutoComplete(context: Context, attrs: AttributeSet? = null, defStyleAttr: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0)` |

### Properties

| Name | Summary |
|---|---|
| [listener](listener.md) | `lateinit var listener: AutoCompleteListener` |

### Functions

| Name | Summary |
|---|---|
| [setAutoCompleteItems](set-auto-complete-items.md) | This method sets the items to display in the adapter.`fun setAutoCompleteItems(items: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
