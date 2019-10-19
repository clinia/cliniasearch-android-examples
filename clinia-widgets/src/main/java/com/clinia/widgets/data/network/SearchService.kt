package com.clinia.widgets.data.network

import com.clinia.widgets.data.SearchResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

const val token =
    "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6ImFmZDkzZjhlODRiNGUyOWQwMDIyMmQxOGVjNzQ2Njc0IiwidHlwIjoiSldUIn0.eyJuYmYiOjE1NzE0MjQzNzIsImV4cCI6MTU3NDAxNjM3MiwiaXNzIjoiaHR0cHM6Ly9hY2NvdW50cy5jbGluaWEuZGV2IiwiYXVkIjpbImh0dHBzOi8vYWNjb3VudHMuY2xpbmlhLmRldi9yZXNvdXJjZXMiLCJjYXRhbG9nIl0sImNsaWVudF9pZCI6ImRldiIsInN1YiI6ImEzNWNkMzU0LWJmMDgtNGZjMS1hN2RjLWIxMGE5YTAwY2MxNSIsImF1dGhfdGltZSI6MTU3MTQyNDM3MiwiaWRwIjoibG9jYWwiLCJ0eXBlIjoicGFydG5lciIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJsb2NhbGUiOiJmciIsImVtYWlsIjoiYWRtaW5Ad3Vuc2NoLWluYy5jb20iLCJnaXZlbl9uYW1lIjoiQWRtaW4iLCJmYW1pbHlfbmFtZSI6Ild1bnNjaCBJbmMiLCJuYW1lIjoiQWRtaW4gV3Vuc2NoIEluYyIsInJvbGUiOiJhZG1pbiIsInBhcnRuZXIiOiJXVU5TQ0gtSU5DIiwic2NvcGUiOlsib3BlbmlkIiwiY2F0YWxvZyIsIm9mZmxpbmVfYWNjZXNzIl0sImFtciI6WyJwd2QiXSwibW9kdWxlcyI6W119.SxMRLqkHhvk2e9PINCGuHDvG6v4T0h5XSvR5dqN9crYiYhHSLHvse6I8JFW9xqEpLYJepJv7F0umiAu72PbFuKKWDrsrUvQIlOCER1VGxQ4SIDU2LAgz2wbGI75Y3Mko3ejxVRPNV-mydjDbbSv8HzpEvkjHAD4eVnOuEwSPGVu6RjOeSq6wpTZufZtOLPqlkkXZr01IjyxRPmC_8qcIb36W7baZGWIv19xss3esBhjIQI0r86GciR1mY8pski-wuvuAQCsbK5Kb_Xgf7fl4DSlX1PZMPqAV-n-IpqzgHB8H1Ykpa6m7WFBnlSJrhDLWOovgDs1wOjHjBot-ZeKAaw"
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
    val page: Int? = null,
    val perPage: Int? = null,
    var filters: Filters? = null
)

@Suppress("ArrayInDataClass")
data class Filters(
    val location: String?,
    val types: Array<String>? = null
)