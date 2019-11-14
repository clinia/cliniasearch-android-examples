package com.clinia.widgets.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.clinia.widgets.R
import com.clinia.widgets.data.HealthFacility
import com.clinia.widgets.ui.main.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.results_map.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Marker


class ResultsMapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var viewModel: MainViewModel
    private var adapter: ResultAdapter? = null

    private var map: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.results_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }
        context?.let {
            adapter = ResultAdapter(it, mutableListOf())
            resultsList.adapter = adapter
            resultsList.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
        resultsMap.onCreate(savedInstanceState)
        resultsMap.getMapAsync(this)

        viewModel.getSearchData().observe(this, Observer {
            adapter?.setData(it.records as MutableList<HealthFacility>)
            // Creating a marker
            for (record in it.records as MutableList<HealthFacility>) {
                record.geoPoint?.let {
                    val markerOptions = MarkerOptions()
                        .position(
                            LatLng(
                                record.geoPoint.lat.toDouble(),
                                record.geoPoint.lng.toDouble()
                            )
                        )
                    map?.addMarker(markerOptions)
                }
            }
        })
//        fab.setOnClickListener{
////            resultsList.visibility = if (resultsList.isVisible) View.GONE else View.VISIBLE
//        }
    }

    override fun onMapReady(p0: GoogleMap?) {
        map = p0
        map?.uiSettings?.isMyLocationButtonEnabled = false
        map?.isMyLocationEnabled = true

        map?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.mapstyle))
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(45.530243, -73.565260), 13f))

        //TODO: add markers
    }

    fun onMarkerClick(marker: Marker): Boolean {
        //TODO: scroll list on marker click
        return false
    }

    override fun onResume() {
        resultsMap.onResume()
        super.onResume()
    }

    companion object {
        fun newInstance() = ResultsMapFragment()
    }
}