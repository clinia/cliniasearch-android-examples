package com.clinia.widgets.ui.main

import android.app.Application
import android.location.Location
import androidx.lifecycle.*
import com.clinia.widgets.data.*
import com.clinia.widgets.data.network.PlaceSuggestionRequest
import com.clinia.widgets.data.network.QuerySuggestionRequest
import com.clinia.widgets.data.network.SingleIndexSearchRequest

import com.google.android.gms.location.LocationServices

/**
 * Used to access the data and display the data inside the ui components.
 *
 * @constructor The constructor takes an Application object to be used for search using the device location.
 *
 * @param application
 */
class CliniaViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var dataRepository: SearchDataRepository

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

    /**
     * Reset the repository with the new environent variables.
     *
     * @param application Application id.
     * @param apiKey Api key.
     * @param endpoint Base url.
     */
    fun setEnvironment(application: String, apiKey: String, endpoint: String) {
        dataRepository = SearchDataRepository(application, apiKey, endpoint)
    }

    /**
     * Triggers a search with the saved search parameters stored inside the view model.
     */
    private fun search() {
        search(query, locationQuery)
    }


    /**
     *  Gets the updated search data response after a search call to the API.
     *
     * @return LiveData containing a SearchResponse object
     */
    fun getSearchData(): LiveData<SearchResponse> {
        search(query = query, location = lastLocation)
        return searchData
    }

    /**
     * Triggers a query suggestions call.
     *
     * @param query The current input to be completed.
     * @param preTag The tag marking the start of the matching substring in the returned suggestions,
     * to be used for formatting. Can be an HTML tag.
     * @param postTag The tag marking the end of the matching substring in the returned suggestions,
     * to be used for formatting. Can be an HTML tag.
     * @return LiveData containing a list of QuerySuggestion objects
     */
    fun querySuggest(
        query: String,
        preTag: String? = null,
        postTag: String? = null
    ): LiveData<List<QuerySuggestion>> {
        querySuggest(QuerySuggestionRequest(query, preTag, postTag))
        return querySuggestions
    }

    /**
     * Triggers a place suggestions call.
     *
     * @param location The current location input to be completed.
     * @param country This parameters restrains the suggestions to a single country. Default value is null, for global results.
     * @return LiveData containing a list of PlaceSuggestion objects
     */
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

    /**
     * Takes the current query and location search parameters and return the next page
     * of results inside a LiveData<SearchResponse> variable, if there are any.
     * If not, the liveData contains an empty array, along with the metadata associated with the search.
     *
     * @return a LiveData containing a SearchResponse data object
     */
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
