package com.clinia.widgets.data.network

import com.clinia.widgets.data.SearchResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

const val token =
    "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjYzMDQyZWI4NDhmZmI5ZjI2OTAyNTIxN2EzOTYzOGQyIiwidHlwIjoiSldUIn0.eyJuYmYiOjE1NzExNjM4NzMsImV4cCI6MTU3Mzc1NTg3MywiaXNzIjoiaHR0cHM6Ly9hY2NvdW50cy5jbGluaWEuZGV2IiwiYXVkIjpbImh0dHBzOi8vYWNjb3VudHMuY2xpbmlhLmRldi9yZXNvdXJjZXMiLCJjYXRhbG9nIl0sImNsaWVudF9pZCI6ImRldiIsInN1YiI6IjM1ZDE5NzlhLTM1NmYtNGMxYS05NGIwLTAwODMyNjE3YmI1MyIsImF1dGhfdGltZSI6MTU3MTE2Mzg3MywiaWRwIjoibG9jYWwiLCJ0eXBlIjoicGFydG5lciIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJsb2NhbGUiOiJlbiIsImVtYWlsIjoiYWRtaW5Ad3Vuc2NoLWluYy5jb20iLCJnaXZlbl9uYW1lIjoiQWRtaW4iLCJmYW1pbHlfbmFtZSI6Ild1bnNjaCBJbmMiLCJuYW1lIjoiQWRtaW4gV3Vuc2NoIEluYyIsInJvbGUiOiJhZG1pbiIsInBhcnRuZXIiOiJXVU5TQ0gtSU5DIiwic2NvcGUiOlsib3BlbmlkIiwiY2F0YWxvZyIsIm9mZmxpbmVfYWNjZXNzIl0sImFtciI6WyJwd2QiXSwibW9kdWxlcyI6W119.LtSzXdlUMMkkw5YPGk_hYEP0phWs--DdOrRUFJLbEgbzTv7Y8I1Xle2SWgrmD3Rq1QuCcEpS0QRXU_5lVFvCF0ar1lgFHN1pgrVD0evAL-8uVyA-TlW_aAYVQTvZduFKNJ9GTanMr0vWXDFYSYwoSMVNSOInoj7fOhl-ryofofa5Tz-uCOM1Mwyb8MzVnkfeXd_2GsMrStqCSoHXupyrGE2t5tjx2k-hnQf8rc5sTZxIPie85QXzVs_8A1JFqf6CZ26cfm4oEIAUChvDmQ6lRjQ_vzEi1_HT6cbrovdV6S0uZUHsjPQqAL_W3mMVlCKBWYdgtqhIexlxI9GeW9SPjw"
const val contentType = "application/json"

interface SearchService {

    @POST("search/v1/search?")
    fun listClinics(
        @Body searchRequestBody: SearchRequestBody,
        @Header("Content-Type") auth: String = contentType,
        @Header("Authorization") content: String = token
    ): Call<SearchResponse>
}

data class SearchRequestBody(
    val q: String? = "",
    val page: Int? = 1,
    val perPage: Int? = 10
)