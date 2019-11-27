package com.clinia.demoApp

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.clinia.demoApp.ui.main.MainFragment
import com.clinia.widgets.BuildConfig
import com.clinia.widgets.R
import com.clinia.widgets.data.HealthFacility
import com.clinia.widgets.data.SearchResponse
import com.clinia.widgets.ui.main.CliniaViewModel
import com.clinia.widgets.ui.view.OnMapMovedListener
import com.clinia.widgets.ui.view.ResultsMapFragment
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(), OnMapMovedListener {

    private lateinit var viewModel: CliniaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        viewModel = ViewModelProviders.of(this).get(CliniaViewModel::class.java)
        viewModel.setEnvironment(BuildConfig.application, BuildConfig.apiKey, BuildConfig.endpoint)
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1)
        }

        fab.setOnClickListener {f ->
            supportFragmentManager.findFragmentById(R.id.container)?.let {
                if (it.isVisible && it is ResultsMapFragment) {
                    it.onMapMovedListener = this
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MainFragment.newInstance())
                        .commit()
                    f.animate().translationY(0f)
                    fab.setImageResource(R.drawable.ic_map)
                }
                else {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, ResultsMapFragment.newInstance())
                        .commit()
                    f.animate().translationY(-520f)
                    fab.setImageResource(R.drawable.ic_list)

                }
            }
        }
    }

    override fun onMapMoved(target: LatLng): LiveData<SearchResponse> {
        viewModel.locationQuery = "${target.latitude}, ${target.longitude}"
        return viewModel.getSearchData()
    }
}
