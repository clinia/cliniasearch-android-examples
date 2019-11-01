package com.clinia.demoApp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.clinia.widgets.R
import com.clinia.widgets.ui.main.MainViewModel
import com.clinia.widgets.ui.view.ResultAdapter
import com.clinia.widgets.ui.view.SearchBar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), SearchBar.SearchBarListener {

    private lateinit var viewModel: MainViewModel

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
        resultsMap.onCreate(null)
        resultsMap.getMapAsync{
            it.setMinZoomPreference(12f)
            it.moveCamera(CameraUpdateFactory.newLatLng(LatLng(45.5303026, -73.565657)))
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
        fab.setOnClickListener {
            resultsMap.visibility = if (resultsMap.isVisible) View.GONE else View.VISIBLE
        }
    }

    override fun onEnter(query: String, location: String) {
        viewModel.getSearchData(query, location).observe(this, Observer {
                adapter?.setData(it.records)
        })
    }

    override fun onAutoCompleteSelect() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTextChange() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResult() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
