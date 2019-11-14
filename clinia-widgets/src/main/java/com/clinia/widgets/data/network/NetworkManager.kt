package com.clinia.widgets.data.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object NetworkManager{

//    private const val prefixURL = "https://api.clinia.dev/"
    private const val prefixURL = "https://api.staging.clinia.ca/"
//    private const val prefixURL = "https://api.partner.staging.clinia.ca/"

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(prefixURL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    var searchService: SearchService = retrofit.create(SearchService::class.java)

}