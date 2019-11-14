package com.clinia.widgets

import androidx.test.filters.SmallTest
import com.clinia.widgets.data.*
import com.clinia.widgets.data.network.MultiIndexesSearchRequestBody
import com.clinia.widgets.data.network.NetworkManager
import com.clinia.widgets.data.network.QuerySuggestionRequestBody
import com.clinia.widgets.data.network.SingleIndexSearchRequestBody

import org.junit.Test
import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CountDownLatch

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@SmallTest
class ExampleInstrumentedTest {
    @Test
    fun searchSingleIndexHealthFacility() {
        val signal = CountDownLatch(1)
        NetworkManager.searchService.searchHealthFacilities(SingleIndexSearchRequestBody(params = "query=&queryType=prefixLast")).enqueue(object : Callback<SearchResponse> {
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
                    body?.records?.all { record -> record.documentType == DocumentType.HEALTH_FACILITY }?.let { all -> assertTrue(all) }

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
        NetworkManager.searchService.searchProfessionnals(SingleIndexSearchRequestBody(params = "query=&queryType=prefixLast")).enqueue(object : Callback<SearchResponse> {
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
                    body?.records?.all { record -> record.documentType == DocumentType.PROFESSIONAL }?.let { all -> assertTrue(all) }

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
        NetworkManager.searchService.search(MultiIndexesSearchRequestBody(arrayOf(SingleIndexSearchRequestBody("health_facility","query=&queryType=prefixLast"), SingleIndexSearchRequestBody("professional", "query=&queryType=prefixLast")))).enqueue(object : Callback<MultiSearchResponse> {
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
        NetworkManager.querySuggestionService.suggest(QuerySuggestionRequestBody("query=")).enqueue(object : Callback<Array<QuerySuggestion>> {
            override fun onFailure(call: Call<Array<QuerySuggestion>>?, t: Throwable?) {
                t?.let { fail("onFailure message: ${t.message}") }
                signal.countDown()
            }

            override fun onResponse(call: Call<Array<QuerySuggestion>>?, response: Response<Array<QuerySuggestion>>?) {
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
        NetworkManager.placeSuggestionService.suggest("Longueuil", "CA", 5).enqueue(object : Callback<Array<PlaceSuggestion>> {
            override fun onFailure(call: Call<Array<PlaceSuggestion>>?, t: Throwable?) {
                t?.let { fail("onFailure message: ${t.message}") }
                signal.countDown()
            }

            override fun onResponse(call: Call<Array<PlaceSuggestion>>?, response: Response<Array<PlaceSuggestion>>?) {
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
