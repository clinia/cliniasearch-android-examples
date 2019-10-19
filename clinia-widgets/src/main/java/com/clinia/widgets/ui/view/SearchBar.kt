package com.clinia.widgets.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import com.clinia.widgets.R
import kotlinx.android.synthetic.main.view_search.view.*

//class SearchBar @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
//) : LinearLayout(context, attrs, defStyleAttr) {

class SearchBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private fun getQuery() = if (searchEditText?.text.isNullOrEmpty()) "" else  searchEditText?.text.toString()
    private fun getLocation() = if (locationEditText?.text.isNullOrEmpty()) "" else locationEditText?.text.toString()

    lateinit var searchListener: SearchListener

    init {
        inflate(context, R.layout.view_search, this)

        context.obtainStyledAttributes(attrs, R.styleable.SearchBar).apply {
            try {
                searchEditText.hint = getString(R.styleable.SearchBar_hint)
                locationEditText.hint = getString(R.styleable.SearchBar_locationHint)
            } finally {
                recycle()
            }
        }
        searchEditText.setOnEditorActionListener { _, p1, _ ->
            if (p1 == EditorInfo.IME_ACTION_SEARCH)
                searchListener.search(getQuery(), getLocation())
            false
        }
        locationEditText.setOnEditorActionListener { _, p1, _ ->
            if (p1 == EditorInfo.IME_ACTION_SEARCH)
                searchListener.search(getQuery(), getLocation())
            false
        }
    }

    interface SearchListener {
        fun search(query: String, location: String)
    }
}