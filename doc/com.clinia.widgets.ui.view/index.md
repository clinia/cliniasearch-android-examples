[Widgets](../index.md) / [com.clinia.widgets.ui.view](./index.md)

## Package com.clinia.widgets.ui.view

### Types

| Name | Summary |
|---|---|
| [AutoComplete](-auto-complete/index.md) | The AutoComplete Widget to be used with the CliniaViewModel to display query and location suggestions to the user.`class AutoComplete : LinearLayout` |
| [AutoCompleteAdapter](-auto-complete-adapter/index.md) | The AutoCompleteAdapter is intended for use with the AutoComplete widget. It is a custom class extending a RecyclerView  Adapter with a generic ViewHolder accepting both QuerySuggestion and PlaceSuggestion objects.`class AutoCompleteAdapter : Adapter<`[`BaseViewHolder`](-base-view-holder/index.md)`<*>>` |
| [BaseViewHolder](-base-view-holder/index.md) | `abstract class BaseViewHolder<T> : ViewHolder` |
| [LocationSearchBar](-location-search-bar/index.md) | The LocationSearchBar is a widget intended to be used with the CliniaViewModel to get a location input from the user.`class LocationSearchBar : LinearLayout` |
| [ResultAdapter](-result-adapter/index.md) | The ResultAdapter class in intended for use with the ResultsList widget to display search results to the user and allow user interaction.`class ResultAdapter : Adapter<ViewHolder>` |
| [ResultsList](-results-list/index.md) | The ResultsList widget is intended for use with the ResultsAdapter and CliniaViewModel classes.`class ResultsList : RecyclerView` |
| [ResultsMapFragment](-results-map-fragment/index.md) | The ResultsMapFragment can be used with the CliniaViewModel and ResultsAdapter classes to display the search results on a custom styled map with a ResultsList featured at the bottom.`class ResultsMapFragment : Fragment, OnMapReadyCallback, OnMarkerClickListener` |
| [SearchBar](-search-bar/index.md) | The SearchBar is a widget intended to be used with the CliniaViewModel to get a query input from the user.`class SearchBar : LinearLayout` |

### Extensions for External Classes

| Name | Summary |
|---|---|
| [com.google.android.gms.maps.model.Marker](com.google.android.gms.maps.model.-marker/index.md) |  |
