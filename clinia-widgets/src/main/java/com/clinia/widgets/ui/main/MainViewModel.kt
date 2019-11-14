package com.clinia.widgets.ui.main

import android.app.Application
import android.location.Location
import androidx.lifecycle.*
import com.clinia.widgets.data.QuerySuggestResponse
import com.clinia.widgets.data.SearchResponse
import com.clinia.widgets.data.SearchDataRepository
import com.clinia.widgets.data.network.Filters
import com.clinia.widgets.data.network.QuerySuggestRequestBody
import com.clinia.widgets.data.network.SearchRequestBody
import com.google.android.gms.location.LocationServices

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val dataRepository: SearchDataRepository = SearchDataRepository()

    var query: String = ""
    var locationQuery: String = ""
    private var lastLocation: Location? = null
    private var searchData = MutableLiveData<SearchResponse>()
    private var querySuggestions = MutableLiveData<QuerySuggestResponse>()

    init {
        LocationServices.getFusedLocationProviderClient(application)
            .lastLocation.addOnSuccessListener {
            lastLocation = it
        }
        search(query, lastLocation)
    }

    //call this method to trigger a search with the saved parameters
    fun search() {
        search(query, locationQuery)
    }

    //call this method to access the data
    fun getSearchData(): LiveData<SearchResponse> = searchData

    fun querySuggest(
        query: String,
        preTag: String? = null,
        postTag: String? = null
    ): LiveData<QuerySuggestResponse> {
        querySuggest(QuerySuggestRequestBody(query, preTag, postTag))
        return querySuggestions
    }

    private fun search(query: String?, location: Location?) {
        val search = SearchRequestBody(query)
        location?.let {
            search.filters = Filters("${it.latitude}, ${it.longitude}")
        }
        loadData(search)
    }

    private fun search(query: String?, location: String?) {
        val search = SearchRequestBody(query)
        location?.let {
            if (it.isBlank() or it.isEmpty()) {
                locationQuery = it
                //TODO: change to coordinates when backend supports it
//            search(query, lastLocation)
                search.filters = Filters(it)
            } else {
                search.filters = Filters(it)
            }
        }
        loadData(search)
    }

    private fun loadData(search: SearchRequestBody) {
        searchData = dataRepository.getSearchData(search)
    }

    private fun querySuggest(query: QuerySuggestRequestBody) {
        querySuggestions = dataRepository.getQuerySuggestions(query)
    }
}
