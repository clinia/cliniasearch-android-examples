[Widgets](../../index.md) / [com.clinia.widgets.ui.view](../index.md) / [ResultsMapFragment](./index.md)

# ResultsMapFragment

`class ResultsMapFragment : Fragment, OnMapReadyCallback, OnMarkerClickListener`

The ResultsMapFragment can be used with the CliniaViewModel and ResultsAdapter classes to display
the search results on a custom styled map with a ResultsList featured at the bottom.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The ResultsMapFragment can be used with the CliniaViewModel and ResultsAdapter classes to display the search results on a custom styled map with a ResultsList featured at the bottom.`ResultsMapFragment()` |

### Functions

| Name | Summary |
|---|---|
| [onCreateView](on-create-view.md) | `fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?` |
| [onMapReady](on-map-ready.md) | `fun onMapReady(p0: GoogleMap?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onMarkerClick](on-marker-click.md) | `fun onMarkerClick(marker: Marker): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onResume](on-resume.md) | `fun onResume(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onViewCreated](on-view-created.md) | `fun onViewCreated(view: View, savedInstanceState: Bundle?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [newInstance](new-instance.md) | `fun newInstance(): `[`ResultsMapFragment`](./index.md) |
