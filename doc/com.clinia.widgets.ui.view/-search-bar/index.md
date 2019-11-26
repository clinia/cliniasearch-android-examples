[Widgets](../../index.md) / [com.clinia.widgets.ui.view](../index.md) / [SearchBar](./index.md)

# SearchBar

`class SearchBar : LinearLayout`

The SearchBar is a widget intended to be used with the CliniaViewModel
to get a query input from the user.

### Types

| Name | Summary |
|---|---|
| [SearchBarListener](-search-bar-listener/index.md) | Implement this interface to access various callbacks from the search bar for user interaction`interface SearchBarListener` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The SearchBar inherits the base constructors from the LinearLayout Class`SearchBar(context: Context, attrs: AttributeSet? = null, defStyleAttr: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0)` |

### Properties

| Name | Summary |
|---|---|
| [listener](listener.md) | `lateinit var listener: SearchBarListener` |

### Functions

| Name | Summary |
|---|---|
| [setQuery](set-query.md) | Sets the text displayed in the search bar`fun setQuery(query: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
