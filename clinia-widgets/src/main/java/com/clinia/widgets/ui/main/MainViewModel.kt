package com.clinia.widgets.ui.main

import android.app.Application
import android.location.Location
import androidx.lifecycle.*
import com.clinia.widgets.data.Metadata
import com.clinia.widgets.data.PlaceSuggestion
import com.clinia.widgets.data.QuerySuggestion
import com.clinia.widgets.data.SearchResponse
import com.clinia.widgets.data.SearchDataRepository
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
        postTag: String? = null
    ): LiveData<List<QuerySuggestion>> {
        querySuggest(QuerySuggestionRequest(query, preTag, postTag))
        return querySuggestions
    }

    fun placeSuggest(
        location: String,
        country: String?
    ): LiveData<List<PlaceSuggestion>> {
        placeSuggest(PlaceSuggestionRequest(location, country))
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

    fun loadMore() {
        searchData.value?.meta?.page?.let { page ->
            val newSearchData = dataRepository.searchHealthFacilities(
                SingleIndexSearchRequest(
                    query = query,
                    location = locationQuery,
                    page = page + 1
                )
            )
            newSearchData.value?.records?.let { records ->
                searchData.value?.records?.addAll(records)
            }
        }
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
