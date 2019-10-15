package com.clinia.widgets.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clinia.widgets.data.SearchResponse
import com.clinia.widgets.data.SearchDataRepository

class MainViewModel: ViewModel() {

    private val dataRepository: SearchDataRepository = SearchDataRepository()

    private val _data = MutableLiveData<SearchResponse>()
    val response : LiveData<SearchResponse>
    get() = _data

    fun getSearchData(query: String?, currentPage: Int?, perPage: Int?): LiveData<SearchResponse> {
        return dataRepository.getSearchData(query, currentPage, perPage)
    }

}
