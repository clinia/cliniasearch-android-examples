package com.clinia.widgets.data.network

import com.clinia.widgets.data.RecordAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val application = "TODO"
const val apiKey = "AAW3nfvI79tj4LzECYZSEbDP7lqBpFd5"
const val contentType = "application/json"

/**
 * The NetworkManager Object contains the three services exposed by the Clinia API for use by the
 * Vision library.
 */
object NetworkManager{

    private const val prefixURL = "https://api.partner.staging.clinia.ca/"

    private var moshi = Moshi.Builder()
            .add(QuerySuggestionAdapter())
            .add(SingleIndexAdapter())
            .add(RecordAdapter())
            .build()

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(prefixURL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    var searchService: SearchService = retrofit.create(SearchService::class.java)
    var querySuggestionService: QuerySuggestionService = retrofit.create(QuerySuggestionService::class.java)
    var placeSuggestionService: PlaceSuggestionService = retrofit.create(PlaceSuggestionService::class.java)
}