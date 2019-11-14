package com.clinia.widgets.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.clinia.widgets.data.network.NetworkManager
import com.clinia.widgets.data.network.QuerySuggestionRequestBody
import com.clinia.widgets.data.network.SingleIndexSearchRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchDataRepository {

    fun getSearchData(requestBody: SingleIndexSearchRequestBody): MutableLiveData<SearchResponse> {
        val data = MutableLiveData<SearchResponse>()
        //get clinics network call
        NetworkManager.searchService.searchHealthFacilities(requestBody).enqueue(object : Callback<SearchResponse> {
            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                t?.let { Log.e(this.javaClass.simpleName, "onFailure message: ${t.message}") }
            }

            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                response?.let {
                    if (it.isSuccessful)
                        data.postValue(it.body())
                    else
                        Log.e(this.javaClass.simpleName, "Request failed with error ${it.code()}: ${it.errorBody()}")
                }
            }
        })
        return data
    }

    fun getQuerySuggestions(requestBody: QuerySuggestionRequestBody): MutableLiveData<Array<QuerySuggestion>> {
        val data = MutableLiveData<Array<QuerySuggestion>>()
        //get clinics network call
        NetworkManager.querySuggestService.querySuggest(requestBody).enqueue(object : Callback<Array<QuerySuggestion>> {
            override fun onFailure(call: Call<Array<QuerySuggestion>>?, t: Throwable?) {
                t?.let { Log.e(this.javaClass.simpleName, "onFailure message: ${t.message}") }
            }

            override fun onResponse(call: Call<Array<QuerySuggestion>>?, response: Response<Array<QuerySuggestion>>?) {
                response?.let {
                    if (it.isSuccessful)
                        data.postValue(it.body())
                    else
                        Log.e(this.javaClass.simpleName, "Request failed with error ${it.code()}: ${it.errorBody()}")
                }
            }
        })
        return data
    }
}