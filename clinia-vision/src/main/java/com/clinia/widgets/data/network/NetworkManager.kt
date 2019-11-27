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
    var prefixURL: String = ""
        set(value) {
            field = value
            retrofit = Retrofit.Builder()
                .baseUrl(value)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
            searchService = retrofit.create(SearchService::class.java)
            querySuggestionService = retrofit.create(QuerySuggestionService::class.java)
            placeSuggestionService = retrofit.create(PlaceSuggestionService::class.java)
        }

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
    private lateinit var retrofit: Retrofit

    /**
     * Search service
     */
    lateinit var searchService: SearchService
    /**
     * Query suggestions service
     */
    lateinit var querySuggestionService: QuerySuggestionService
    /**
     * Places suggestions service
     */
    lateinit var placeSuggestionService: PlaceSuggestionService
}