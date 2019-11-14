package com.clinia.widgets

import com.clinia.widgets.data.network.QuerySuggestionAdapter
import com.clinia.widgets.data.network.QuerySuggestionRequest
import com.clinia.widgets.data.network.SingleIndexAdapter
import com.clinia.widgets.data.network.SingleIndexSearchRequest
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AdapterUnitTests {
    @Test
    fun singleIndexAdapter_toJson_urlEncode() {
        val adapter = SingleIndexAdapter()

        val body = adapter.toJson(SingleIndexSearchRequest("professional", "query",0,20,"prefix_last", listOf("name")))

        assertEquals("query=query&page=0&perPage=20&queryType=prefix_last&searchFields=%5Bname%5D", body.params)
    }

    @Test
    fun querySuggestionAdapter_toJson_urlEncode() {
        val adapter = QuerySuggestionAdapter()

        val body = adapter.toJson(QuerySuggestionRequest("professional", "<strong>","</strong>",20))

        assertEquals("query=professional&highlightPreTag=%3Cstrong%3E&highlightPostTag=%3C%2Fstrong%3E&size=20", body.params)
    }
}
