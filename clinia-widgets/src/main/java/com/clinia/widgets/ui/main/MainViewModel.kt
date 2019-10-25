package com.clinia.widgets.ui.main

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clinia.widgets.data.SearchResponse
import com.clinia.widgets.data.SearchDataRepository
import com.clinia.widgets.data.network.Filters
import com.clinia.widgets.data.network.SearchRequestBody

class MainViewModel: ViewModel() {

    private val dataRepository: SearchDataRepository = SearchDataRepository()

//    //TODO: refactor to use this getter in main activity
//    private val _data = MutableLiveData<SearchResponse>()
//    val response : LiveData<SearchResponse>
//    get() = _data

    fun getSearchData(query: String?, location: Location?): LiveData<SearchResponse> {
        val search = SearchRequestBody(query)
        location?.let {
            search.filters = Filters("${it.latitude}, ${it.longitude}")
        }
        return dataRepository.getSearchData(search)
    }

    fun getSearchData(query: String?, location: String?): LiveData<SearchResponse> {
        val search = SearchRequestBody(query)
        location?.let {
            search.filters = Filters(location)
        }
        return dataRepository.getSearchData(search)
    }

}
