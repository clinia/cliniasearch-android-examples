package com.clinia.widgets.data.network

import com.clinia.widgets.data.RecordAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * @suppress
 */
const val contentType = "application/json"

/**
 * Contains the three services exposed by the client to interact with the Clinia APIs.
 */
object NetworkManager {

    /**
     * Application Id
     */
    lateinit var application: String
    /**
     * Api key
     */
    lateinit var apiKey: String
    /**
     * Base url
     */
    lateinit var prefixURL: String

    /**
     * @suppress
     */
    private var moshi = Moshi.Builder()
            .add(QuerySuggestionAdapter())
            .add(SingleIndexAdapter())
            .add(RecordAdapter())
            .build()

    /**
     * @suppress
     */
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(prefixURL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    /**
     * Search service
     */
    var searchService: SearchService = retrofit.create(SearchService::class.java)
    /**
     * Query suggestions service
     */
    var querySuggestionService: QuerySuggestionService = retrofit.create(QuerySuggestionService::class.java)
    /**
     * Places suggestions service
     */
    var placeSuggestionService: PlaceSuggestionService = retrofit.create(PlaceSuggestionService::class.java)
}