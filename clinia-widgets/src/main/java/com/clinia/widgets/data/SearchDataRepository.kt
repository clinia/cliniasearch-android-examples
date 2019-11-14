package com.clinia.widgets.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.clinia.widgets.data.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchDataRepository {

    fun searchHealthFacilities(request: SingleIndexSearchRequest): MutableLiveData<SearchResponse> {
        val data = MutableLiveData<SearchResponse>()
        NetworkManager.searchService.searchHealthFacilities(request).enqueue(object : Callback<SearchResponse> {
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

    fun searchProfessionals(request: SingleIndexSearchRequest): MutableLiveData<SearchResponse> {
        val data = MutableLiveData<SearchResponse>()
        NetworkManager.searchService.searchProfessionnals(request).enqueue(object : Callback<SearchResponse> {
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

    fun search(request: MultiIndexesSearchRequest): MutableLiveData<MultiSearchResponse> {
        val data = MutableLiveData<MultiSearchResponse>()
        NetworkManager.searchService.search(request).enqueue(object : Callback<MultiSearchResponse> {
            override fun onFailure(call: Call<MultiSearchResponse>?, t: Throwable?) {
                t?.let { Log.e(this.javaClass.simpleName, "onFailure message: ${t.message}") }
            }

            override fun onResponse(call: Call<MultiSearchResponse>?, response: Response<MultiSearchResponse>?) {
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

    fun getQuerySuggestions(request: QuerySuggestionRequest): MutableLiveData<Array<QuerySuggestion>> {
        val data = MutableLiveData<Array<QuerySuggestion>>()
        NetworkManager.querySuggestionService.suggest(request).enqueue(object : Callback<Array<QuerySuggestion>> {
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

    fun getPlaceSuggestions(request: PlaceSuggestionRequest): MutableLiveData<Array<PlaceSuggestion>> {
        val data = MutableLiveData<Array<PlaceSuggestion>>()
        NetworkManager.placeSuggestionService.suggest(request.input, request.country, request.size).enqueue(object : Callback<Array<PlaceSuggestion>> {
            override fun onFailure(call: Call<Array<PlaceSuggestion>>?, t: Throwable?) {
                t?.let { Log.e(this.javaClass.simpleName, "onFailure message: ${t.message}") }
            }

            override fun onResponse(call: Call<Array<PlaceSuggestion>>?, response: Response<Array<PlaceSuggestion>>?) {
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