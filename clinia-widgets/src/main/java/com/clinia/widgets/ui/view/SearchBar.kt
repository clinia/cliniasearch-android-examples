package com.clinia.widgets.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
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

    lateinit var searchListener: SearchBarListener
    private var isQueryRequired: Boolean = false
    private var isLocationRequired: Boolean = false

    init {
        inflate(context, R.layout.view_search, this)

        context.obtainStyledAttributes(attrs, R.styleable.SearchBar).apply {
            try {
                searchEditText.hint = getString(R.styleable.SearchBar_hint)
                locationEditText.hint = getString(R.styleable.SearchBar_locationHint)

                when (getInt(R.styleable.SearchBar_searchInput, 1)) {
                    0 -> searchInputLayout.visibility = View.GONE
                    1 -> searchInputLayout.visibility = View.VISIBLE
                    2 -> {
                        searchInputLayout.visibility = View.VISIBLE
                        isLocationRequired = true
                    }
                }
                when (getInt(R.styleable.SearchBar_locationInput, 1)) {
                    0 -> locationInputLayout.visibility = View.GONE
                    1 -> locationInputLayout.visibility = View.VISIBLE
                    2 -> {
                        locationInputLayout.visibility = View.VISIBLE
                        isQueryRequired = true
                    }
                }
            } finally {
                recycle()
            }
        }
        searchEditText.setOnEditorActionListener { _, p1, _ ->
            if (p1 == EditorInfo.IME_ACTION_SEARCH)
                searchListener.onEnter(getQuery(), getLocation())
            false
        }
        locationEditText.setOnEditorActionListener { _, p1, _ ->
            if (p1 == EditorInfo.IME_ACTION_SEARCH)
                searchListener.onEnter(getQuery(), getLocation())
            false
        }
    }

    interface SearchBarListener {
        fun onEnter(query: String, location: String)
        fun onAutoCompleteSelect()
        fun onTextChange()
        fun onResult()
        fun onError()
    }
}