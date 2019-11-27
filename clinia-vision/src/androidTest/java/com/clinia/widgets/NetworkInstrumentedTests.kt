package com.clinia.widgets

import androidx.test.filters.SmallTest
import com.clinia.widgets.data.*
import com.clinia.widgets.data.network.MultiIndexesSearchRequest
import com.clinia.widgets.data.network.NetworkManager
import com.clinia.widgets.data.network.QuerySuggestionRequest
import com.clinia.widgets.data.network.SingleIndexSearchRequest

import org.junit.Test
import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CountDownLatch
import kotlin.reflect.typeOf

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@SmallTest
class NetworkInstrumentedTests {
    @Test
    fun searchSingleIndexHealthFacility() {
        val signal = CountDownLatch(1)
        NetworkManager.searchService.searchHealthFacilities(SingleIndexSearchRequest("health_facility", "")).enqueue(object : Callback<SearchResponse> {
            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                t?.let { fail("onFailure message: ${t.message}") }
                signal.countDown()
            }

            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                response?.let {
                    assert(it.isSuccessful)

                    val body = response.body()
                    assertNotNull(body)

                    assertFalse(body?.records.isNullOrEmpty())
                    body?.records?.all { record -> record.documentType == DocumentType.HEALTH_FACILITY && record is HealthFacility }?.let { all -> assertTrue(all) }

                    assertNotNull(body?.meta)
                    assertEquals("", body?.meta?.query)
                }
                signal.countDown()
            }
        })
        signal.await()
    }

    @Test
    fun searchSingleIndexProfessional() {
        val signal = CountDownLatch(1)
        NetworkManager.searchService.searchProfessionnals(SingleIndexSearchRequest("professional", "")).enqueue(object : Callback<SearchResponse> {
            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                t?.let { fail("onFailure message: ${t.message}") }
                signal.countDown()
            }

            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                response?.let {
                    assert(it.isSuccessful)

                    val body = response.body()
                    assertNotNull(body)

                    assertFalse(body?.records.isNullOrEmpty())
                    body?.records?.all { record -> record.documentType == DocumentType.PROFESSIONAL && record is Professional }?.let { all -> assertTrue(all) }

                    assertNotNull(body?.meta)
                    assertEquals("", body?.meta?.query)
                }
                signal.countDown()
            }
        })
        signal.await()
    }

    @Test
    fun searchAllIndexes() {
        val signal = CountDownLatch(1)
        NetworkManager.searchService.search(MultiIndexesSearchRequest(listOf(SingleIndexSearchRequest("health_facility",""), SingleIndexSearchRequest("professional", "")))).enqueue(object : Callback<MultiSearchResponse> {
            override fun onFailure(call: Call<MultiSearchResponse>?, t: Throwable?) {
                t?.let { fail("onFailure message: ${t.message}") }
                signal.countDown()
            }

            override fun onResponse(call: Call<MultiSearchResponse>?, response: Response<MultiSearchResponse>?) {
                response?.let {
                    assert(it.isSuccessful)

                    val body = response.body()
                    assertNotNull(body)

                    assertFalse(body?.results.isNullOrEmpty())
                    body?.results?.all { result -> !result.records.isNullOrEmpty() }?.let { all -> assertTrue(all) }
                }
                signal.countDown()
            }
        })
        signal.await()
    }

    @Test
    fun querySuggestions() {
        val signal = CountDownLatch(1)
        NetworkManager.querySuggestionService.suggest(QuerySuggestionRequest("", "<strong>", "</strong>", 5)).enqueue(object : Callback<List<QuerySuggestion>> {
            override fun onFailure(call: Call<List<QuerySuggestion>>?, t: Throwable?) {
                t?.let { fail("onFailure message: ${t.message}") }
                signal.countDown()
            }

            override fun onResponse(call: Call<List<QuerySuggestion>>?, response: Response<List<QuerySuggestion>>?) {
                response?.let {
                    assert(it.isSuccessful)

                    val body = response.body()
                    assertNotNull(body)

                    assertFalse(body.isNullOrEmpty())
                }
                signal.countDown()
            }
        })
        signal.await()
    }

    @Test
    fun placeSuggestions() {
        val signal = CountDownLatch(1)
        NetworkManager.placeSuggestionService.suggest("Longueuil", "CA", 5, listOf("postcode", "place", "neighborhood")).enqueue(object : Callback<List<PlaceSuggestion>> {
            override fun onFailure(call: Call<List<PlaceSuggestion>>?, t: Throwable?) {
                t?.let { fail("onFailure message: ${t.message}") }
                signal.countDown()
            }

            override fun onResponse(call: Call<List<PlaceSuggestion>>?, response: Response<List<PlaceSuggestion>>?) {
                response?.let {
                    assert(it.isSuccessful)

                    val body = response.body()
                    assertNotNull(body)

                    assertFalse(body.isNullOrEmpty())
                }
                signal.countDown()
            }
        })
        signal.await()
    }
}
