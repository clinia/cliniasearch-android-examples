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

    lateinit var listener: SearchBarListener
    private var isRequired: Boolean = false

    init {
        inflate(context, R.layout.view_search, this)

        context.obtainStyledAttributes(attrs, R.styleable.SearchBar).apply {
            try {
                searchEditText.hint = getString(R.styleable.SearchBar_hintSearch)

                when (getInt(R.styleable.SearchBar_statusSearchBar, 1)) {
                    0 -> searchInputLayout.visibility = View.GONE
                    1 -> searchInputLayout.visibility = View.VISIBLE
                    2 -> {
                        searchInputLayout.visibility = View.VISIBLE
                        isRequired = true
                    }
                }
            } finally {
                recycle()
            }
        }
        searchEditText.setOnEditorActionListener { _, p1, _ ->
            if (p1 == EditorInfo.IME_ACTION_SEARCH)
                listener.onSearchBarEnter(getQuery())
            false
        }
    }

    interface SearchBarListener {
        fun onSearchBarEnter(query: String)
        fun onSearchBarTextChange()
    }
}