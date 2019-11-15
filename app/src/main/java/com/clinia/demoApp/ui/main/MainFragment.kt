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
import com.clinia.widgets.data.PlaceSuggestion
import com.clinia.widgets.data.QuerySuggestion
import com.clinia.widgets.ui.main.MainViewModel
import com.clinia.widgets.ui.view.AutoComplete
import com.clinia.widgets.ui.view.LocationSearchBar
import com.clinia.widgets.ui.view.ResultAdapter
import com.clinia.widgets.ui.view.SearchBar
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), SearchBar.SearchBarListener,
    LocationSearchBar.LocationSearchBarListener, AutoComplete.AutoCompleteListener {

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
        autoComplete.listener = this
    }

    override fun onSearchBarFocusChanged(hasFocus: Boolean) {
        autoComplete.visibility = if (hasFocus) View.VISIBLE else View.INVISIBLE
    }

    override fun onSearchBarEnter(query: String) {
        viewModel.query = query
        viewModel.getSearchData().observe(this, Observer {
            adapter?.setData(it.records as MutableList<HealthFacility>)
        })
    }

    override fun onSearchBarTextChange(query: String) {
        autoComplete.visibility = View.VISIBLE
        viewModel.querySuggest(query).observe(this, Observer {
            autoComplete.setAutoCompleteItems(it)
        })
        //TODO: Add search as you type
//        if (searchBar.)
    }

    override fun onLocationSearchBarFocusChanged(hasFocus: Boolean) {
        autoComplete.visibility = if (hasFocus) View.VISIBLE else View.INVISIBLE
    }

    override fun onLocationSearchBarEnter(location: String) {
        viewModel.locationQuery = location
        viewModel.getSearchData().observe(this, Observer {
            adapter?.setData(it.records as MutableList<HealthFacility>)
        })
    }

    override fun onLocationSearchBarTextChange(location: String) {
        autoComplete.visibility = View.VISIBLE
        viewModel.placeSuggest(location, null).observe(this, Observer {
            autoComplete.setAutoCompleteItems(it)
        })
        //TODO: Add search as you type
//        if (searchBar.)
    }

    override fun onAutoCompleteItemClicked(suggestion: Any) {
        if (suggestion is QuerySuggestion) {
            suggestion.suggestion?.let {it ->
                viewModel.query = it
                searchBar.setQuery(it)
            }
        } else if (suggestion is PlaceSuggestion) {
            suggestion.formattedAddress?.let {
                viewModel.locationQuery = it
                locationSearchBar.setLocation(it)
            }
        }
        viewModel.getSearchData().observe(this, Observer {
            adapter?.setData(it.records as MutableList<HealthFacility>)
        })
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
