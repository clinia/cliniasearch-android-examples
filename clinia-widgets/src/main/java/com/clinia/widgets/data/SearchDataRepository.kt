package com.clinia.widgets.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clinia.widgets.data.network.NetworkManager
import com.clinia.widgets.data.network.QuerySuggestRequestBody
import com.clinia.widgets.data.network.SearchRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchDataRepository {

    fun getSearchData(requestBody: SearchRequestBody): MutableLiveData<SearchResponse> {
        val data = MutableLiveData<SearchResponse>()
        //get clinics network call
        NetworkManager.searchService.listClinics(requestBody).enqueue(object : Callback<SearchResponse> {
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

    fun getQuerySuggestions(requestBody: QuerySuggestRequestBody): MutableLiveData<QuerySuggestResponse> {
        val data = MutableLiveData<QuerySuggestResponse>()
        //get clinics network call
        NetworkManager.querySuggestService.querySuggest(requestBody).enqueue(object : Callback<QuerySuggestResponse> {
            override fun onFailure(call: Call<QuerySuggestResponse>?, t: Throwable?) {
                t?.let { Log.e(this.javaClass.simpleName, "onFailure message: ${t.message}") }
            }

            override fun onResponse(call: Call<QuerySuggestResponse>?, response: Response<QuerySuggestResponse>?) {
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