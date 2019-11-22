package com.clinia.demoApp

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.clinia.demoApp.ui.main.MainFragment
import com.clinia.widgets.R
import com.clinia.widgets.ui.main.CliniaViewModel
import com.clinia.widgets.ui.view.ResultsMapFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

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
//        viewModel.setEnvironment(getString(R.string.application), getString(R.string.apiKey), getString(R.string.endpoint))
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
}
