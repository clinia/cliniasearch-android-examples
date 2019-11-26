package com.clinia.widgets.ui.main

import android.app.Application
import android.location.Location
import androidx.lifecycle.*
import com.clinia.widgets.data.*
import com.clinia.widgets.data.network.PlaceSuggestionRequest
import com.clinia.widgets.data.network.QuerySuggestionRequest
import com.clinia.widgets.data.network.SingleIndexSearchRequest

import com.google.android.gms.location.LocationServices

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val dataRepository: SearchDataRepository = SearchDataRepository()

    var query: String = ""
    var locationQuery: String = ""
    private var lastLocation: Location? = null

    private var searchData = MutableLiveData<SearchResponse>()
    private var searchMetadata = MutableLiveData<Metadata>()
    private var querySuggestions = MutableLiveData<List<QuerySuggestion>>()
    private var placeSuggestions = MutableLiveData<List<PlaceSuggestion>>()

    init {
        LocationServices.getFusedLocationProviderClient(application.baseContext)
            .lastLocation.addOnSuccessListener {
            lastLocation = it
        }
        search()
    }

    //call this method to trigger a search with the saved parameters
    private fun search() {
        search(query, locationQuery)
    }

    //call this method to access the data
    fun getSearchData(): LiveData<SearchResponse> {
        search(query = query, location = lastLocation)
        return searchData
    }

    fun querySuggest(
        query: String,
        preTag: String? = null,
        postTag: String? = null,
        size: Int? = null
    ): LiveData<List<QuerySuggestion>> {
        querySuggest(QuerySuggestionRequest(query, preTag, postTag, size))
        return querySuggestions
    }

    fun placeSuggest(
        location: String,
        country: String?,
        types: List<String>? = null,
        size: Int? = null
    ): LiveData<List<PlaceSuggestion>> {
        placeSuggest(PlaceSuggestionRequest(location, country, types, size))
        return placeSuggestions
    }

    private fun search(query: String?, location: Location?) {
        val search: SingleIndexSearchRequest = if (location != null) {
            SingleIndexSearchRequest(
                query = query,
                location = "${location.latitude}, ${location.longitude}"
            )
        } else {
            SingleIndexSearchRequest(query = query, location = locationQuery)
        }
        loadData(search)
    }

    private fun search(query: String?, location: String?) {
        location?.let {
            if (it.isBlank() or it.isEmpty()) {
                search(query = query, location = lastLocation)
            } else {
                loadData(SingleIndexSearchRequest(query = query, location = location))
            }
        }
    }

    fun loadMore(): LiveData<SearchResponse> {
        var currentPage: Int? = null
        searchData.value?.meta?.page?.let { page ->
             currentPage = page + 1
        }
        return dataRepository.searchHealthFacilities(
            SingleIndexSearchRequest(
                query = query,
                location = locationQuery,
                page = currentPage ?: 0
            )
        )
    }

    private fun loadData(search: SingleIndexSearchRequest) {
        searchData = dataRepository.searchHealthFacilities(search)
    }

    private fun querySuggest(query: QuerySuggestionRequest) {
        querySuggestions = dataRepository.getQuerySuggestions(query)
    }

    private fun placeSuggest(query: PlaceSuggestionRequest) {
        placeSuggestions = dataRepository.getPlaceSuggestions(query)
    }

}
