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
import com.clinia.widgets.ui.main.CliniaViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.results_map.*


class ResultsMapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private lateinit var viewModel: CliniaViewModel
    private var adapter: ResultAdapter? = null

    private var map: GoogleMap? = null
    private var previousMarker: Marker? = null
    private var bounds: LatLngBounds? = null

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
            viewModel = ViewModelProviders.of(it).get(CliniaViewModel::class.java)
        }
        context?.let { context ->
            adapter = ResultAdapter(context, mutableListOf()) { moveMapTo(it) }
            resultsList.adapter = adapter
            resultsList.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
        resultsMap.onCreate(savedInstanceState)
        resultsMap.getMapAsync(this)

        viewModel.getSearchData().observe(this, Observer {
            adapter?.setData(it.records as MutableList<HealthFacility>)

            //calculate zoom to show all markers at once
            val builder = LatLngBounds.Builder()
            // Creating markers
            for (record in it.records as MutableList<HealthFacility>) {
                record.geoPoint?.let { geo ->
                    val markerOptions = MarkerOptions()
                        .position(
                            LatLng(
                                geo.lat.toDouble(),
                                geo.lng.toDouble()
                            )
                        )
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker))
                        .alpha(1f)
                    map?.addMarker(markerOptions)
                        ?.tag = record.id
                    builder.include(markerOptions.position)
                }
            }
            map?.setOnMarkerClickListener(this)
            bounds = builder.build()
            map?.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0))
        })
    }

    override fun onMapReady(p0: GoogleMap?) {
        map = p0
        map?.uiSettings?.isMyLocationButtonEnabled = false
        map?.isMyLocationEnabled = true

        map?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.mapstyle))

        //zoom to show all markers
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        previousMarker?.setActive(false)
        marker.setActive(true)
        resultsList.scrollToPosition(
            resultsList.getResult(marker.tag as String)
        )
        previousMarker = marker
        return false
    }

    private fun moveMapTo(healthFacility: HealthFacility) {
        healthFacility.geoPoint?.let {
            map?.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        it.lat.toDouble(),
                        it.lng.toDouble()
                    ), 13f
                )
            )
        }
    }

    override fun onResume() {
        resultsMap.onResume()
        super.onResume()
    }

    companion object {
        fun newInstance() = ResultsMapFragment()
    }
}

fun Marker.setActive(isActive: Boolean) =
    setIcon(BitmapDescriptorFactory.fromResource(if (isActive) R.drawable.ic_marker_active else R.drawable.ic_marker))
