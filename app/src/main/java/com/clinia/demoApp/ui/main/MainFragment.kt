package com.clinia.demoApp.ui.main

import android.app.Activity
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.clinia.widgets.R
import com.clinia.widgets.ui.main.MainViewModel
import com.clinia.widgets.ui.view.ResultAdapter
import com.clinia.widgets.ui.view.SearchBar
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), SearchBar.SearchListener {

    private lateinit var viewModel: MainViewModel
    private var lastLocation: Location? = null

    private var adapter: ResultAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        LocationServices.getFusedLocationProviderClient(context as Activity)
        .lastLocation.addOnSuccessListener {
            lastLocation = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchBar.searchListener = this
        context?.let {
            adapter = ResultAdapter(it, mutableListOf())
            resultsList.adapter = adapter
            resultsList.layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun search(query: String, location: String) {
        if (location.isBlank() or location.isEmpty())
            viewModel.getSearchData(query, lastLocation).observe(this, Observer {
                adapter?.setData(it.records)
            })
        else
            viewModel.getSearchData(query, location).observe(this, Observer {
                adapter?.setData(it.records)
            })
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
