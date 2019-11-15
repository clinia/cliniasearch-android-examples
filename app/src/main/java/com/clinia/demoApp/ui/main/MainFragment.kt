package com.clinia.demoApp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.clinia.widgets.R
import com.clinia.widgets.data.HealthFacility
import com.clinia.widgets.ui.main.MainViewModel
import com.clinia.widgets.ui.view.LocationSearchBar
import com.clinia.widgets.ui.view.ResultAdapter
import com.clinia.widgets.ui.view.SearchBar
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), SearchBar.SearchBarListener, LocationSearchBar.LocationSearchBarListener {

    private lateinit var viewModel: MainViewModel

    private var adapter: ResultAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchBar.listener = this
        locationSearchBar.listener = this
        context?.let {
            adapter = ResultAdapter(it, mutableListOf())
            resultsList.adapter = adapter
            resultsList.layoutManager = LinearLayoutManager(activity)
        }
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
            viewModel.getSearchData().observe(this, Observer { searchResponse ->
                adapter?.setData(searchResponse.records as MutableList<HealthFacility>)
            })
        }
    }

    override fun onSearchBarEnter(query: String) {
        viewModel.query = query
        viewModel.getSearchData().observe(this, Observer {
            adapter?.setData(it.records as MutableList<HealthFacility>)
        })
    }

    override fun onSearchBarTextChange() {
//        if (searchBar.)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLocationSearchBarEnter(location: String) {
        viewModel.locationQuery = location
        viewModel.getSearchData().observe(this, Observer {
            adapter?.setData(it.records as MutableList<HealthFacility>)
        })
    }

    override fun onLocationSearchBarTextChange() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    companion object {
        fun newInstance() = MainFragment()
    }
}
