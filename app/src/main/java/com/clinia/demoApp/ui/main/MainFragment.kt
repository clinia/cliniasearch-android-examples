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
import com.clinia.widgets.ui.main.CliniaViewModel
import com.clinia.widgets.ui.view.*
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), SearchBar.SearchBarListener,
    LocationSearchBar.LocationSearchBarListener, AutoComplete.AutoCompleteListener,
    ResultsList.OnLoadMoreListener {

    private lateinit var viewModel: CliniaViewModel

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
        resultsList.listener = this
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(CliniaViewModel::class.java)
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
        autoComplete.visibility = View.GONE
    }

    override fun onSearchBarTextChange(query: String) {
        autoComplete.visibility = View.VISIBLE
        viewModel.querySuggest(query).observe(this, Observer {
            autoComplete.setAutoCompleteItems(it)
        })
    }

    override fun onSearchBarCleared() {
        autoComplete.visibility = View.GONE
    }

    override fun onLocationSearchBarFocusChanged(hasFocus: Boolean) {
        autoComplete.visibility = if (hasFocus) View.VISIBLE else View.INVISIBLE
    }

    override fun onLocationSearchBarEnter(location: String) {
        viewModel.locationQuery = location
        viewModel.getSearchData().observe(this, Observer {
            adapter?.setData(it.records as MutableList<HealthFacility>)
        })
        autoComplete.visibility = View.GONE
    }

    override fun onLocationSearchBarTextChange(location: String) {
        autoComplete.visibility = View.VISIBLE
        viewModel.placeSuggest(location, "CA").observe(this, Observer {
            autoComplete.setAutoCompleteItems(it)
        })
    }

    override fun onLocationSearchBarCleared() {
        autoComplete.visibility = View.GONE
    }

    override fun onAutoCompleteItemClicked(suggestion: Any) {
        if (suggestion is QuerySuggestion) {
            suggestion.suggestion?.let { it ->
                viewModel.query = it
                searchBar.setQuery(it)
                searchBar.clearFocus()
            }
        } else if (suggestion is PlaceSuggestion) {
            suggestion.formattedAddress?.let {
                viewModel.locationQuery = it
                locationSearchBar.setLocation(it)
                locationSearchBar.clearFocus()
            }
        }
        viewModel.getSearchData().observe(this, Observer {
            adapter?.setData(it.records as MutableList<HealthFacility>)
        })
    }

    override fun onLoadMore() {
        viewModel.loadMore().observe(this, Observer {
            adapter?.addData(it.records as MutableList<HealthFacility>)
        })
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}
