package com.clinia.widgets.ui.main

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.clinia.widgets.data.SearchResponse
import com.clinia.widgets.data.SearchDataRepository
import com.clinia.widgets.data.network.Filters
import com.clinia.widgets.data.network.SearchRequestBody

class MainViewModel: ViewModel() {

    private val dataRepository: SearchDataRepository = SearchDataRepository()

    private var searchData = MutableLiveData<SearchResponse>()

    //call this method to get the data
    fun getSearchData(query: String?, location: Location?): LiveData<SearchResponse> {
        val search = SearchRequestBody(query)
        location?.let {
            search.filters = Filters("${it.latitude}, ${it.longitude}")
        }
        loadData(search)
        return searchData
    }

    //call this method to get the data
    fun getSearchData(query: String?, location: String?): LiveData<SearchResponse> {
        val search = SearchRequestBody(query)
        location?.let {
            search.filters = Filters(location)
        }
        loadData(search)
        return searchData
    }

    private fun loadData(search: SearchRequestBody) {
        searchData = dataRepository.getSearchData(search)
    }

}
