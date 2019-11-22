package com.clinia.widgets.data.network

import com.clinia.widgets.data.RecordAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val application = "TODO"
const val apiKey = "ClM5vDTmS4GWEL0aS7osJaRkowV8McuP"
const val contentType = "application/json"

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