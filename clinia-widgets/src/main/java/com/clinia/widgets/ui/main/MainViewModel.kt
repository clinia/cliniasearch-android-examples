package com.clinia.widgets.ui.main

import android.app.Application
import android.location.Location
import androidx.lifecycle.*
import com.clinia.widgets.data.SearchResponse
import com.clinia.widgets.data.SearchDataRepository
import com.clinia.widgets.data.network.Filters
import com.clinia.widgets.data.network.SearchRequestBody
import com.google.android.gms.location.LocationServices

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val dataRepository: SearchDataRepository = SearchDataRepository()

    private var lastLocation: Location? = null
    private var searchData = MutableLiveData<SearchResponse>()

    init {
        LocationServices.getFusedLocationProviderClient(application)
            .lastLocation.addOnSuccessListener {
            lastLocation = it
        }
    }

    //call this method to get the data
    fun getSearchData(query: String, location: String): LiveData<SearchResponse>{
        return if (location.isBlank() or location.isEmpty()) {
            //TODO: change to coordinates when backend supports it
//            search(query, lastLocation)
            search(query, location)
        } else {
            search(query, location)
        }
    }

    private fun search(query: String?, location: Location?): LiveData<SearchResponse> {
        val search = SearchRequestBody(query)
        location?.let {
            search.filters = Filters("${it.latitude}, ${it.longitude}")
        }
        loadData(search)
        return searchData
    }

    //call this method to get the data
    private fun search(query: String?, location: String?): LiveData<SearchResponse> {
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
