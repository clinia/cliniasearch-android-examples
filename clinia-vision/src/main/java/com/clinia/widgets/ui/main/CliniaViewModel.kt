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

/**
 * The CliniaViewModel class is used to access the various methods calling the Clina API
 *
 * @constructor
 * The constructor takes as parameter an Application object
 *
 * @param application
 */
class CliniaViewModel(application: Application) : AndroidViewModel(application) {

    private val dataRepository: SearchDataRepository = SearchDataRepository()

    var query: String = ""
    var locationQuery: String = ""

    private var searchData = MutableLiveData<SearchResponse>()
    private var searchMetadata = MutableLiveData<Metadata>()
    private var querySuggestions = MutableLiveData<List<QuerySuggestion>>()
    private var placeSuggestions = MutableLiveData<List<PlaceSuggestion>>()

    init {
        search()
    }

    //call this method to trigger a search with the saved parameters
    private fun search() {
        search(query, locationQuery)
    }

    //call this method to access the data
    fun getSearchData(): LiveData<SearchResponse> {
        search()
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
        val search = SingleIndexSearchRequest(query)
//        location?.let {
//            search.filters = Filters("${it.latitude}, ${it.longitude}")
//        }
        loadData(search)
    }

    private fun search(query: String?, location: String?) {
        val search = SingleIndexSearchRequest(query = query, location = location)
//        location?.let {
//            if (it.isBlank() or it.isEmpty()) {
//                locationQuery = it
//                //TODO: change to coordinates when backend supports it
////            search(query, lastLocation)
//                search.filters = Filters(it)
//            } else {
//                search.filters = Filters(it)
//            }
//        }
        loadData(search)
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
