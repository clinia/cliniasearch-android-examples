package com.clinia.widgets.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clinia.widgets.data.network.NetworkManager
import com.clinia.widgets.data.network.SearchRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchDataRepository {

    fun getSearchData(query: String?, currentPage: Int?, perPage: Int?): LiveData<SearchResponse> {
        val data = MutableLiveData<SearchResponse>()
        //get clinics network call
        NetworkManager.searchService.listClinics(SearchRequestBody(query, currentPage, perPage)).enqueue(object : Callback<SearchResponse> {
            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                print(call?.request()?.body())
            }

            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                if (response?.isSuccessful!!) {
                    data.postValue(response.body())
                }
                else {
                    Log.e(this.javaClass.simpleName, "")
                }
            }

        })
        return data
    }
}