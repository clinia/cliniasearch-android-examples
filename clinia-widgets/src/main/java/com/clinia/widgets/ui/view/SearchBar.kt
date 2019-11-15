package com.clinia.widgets.ui.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import com.clinia.widgets.R
import kotlinx.android.synthetic.main.view_search.view.*

class SearchBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private fun getQuery() = if (searchEditText?.text.isNullOrEmpty()) "" else  searchEditText?.text.toString()

    lateinit var listener: SearchBarListener

    init {
        inflate(context, R.layout.view_search, this)

        context.obtainStyledAttributes(attrs, R.styleable.SearchBar).apply {
            try {
                searchEditText.hint = getString(R.styleable.SearchBar_hintSearch)
            } finally {
                recycle()
            }
        }
        searchEditText.setOnFocusChangeListener { _, b ->
            listener.onSearchBarFocusChanged(b)
        }
//        searchEditText.doOnTextChanged { text, start, count, after ->
//        }
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                listener.onSearchBarTextChange(getQuery())
            }
        })
        searchEditText.setOnEditorActionListener { _, p1, _ ->
            if (p1 == EditorInfo.IME_ACTION_SEARCH)
                listener.onSearchBarEnter(getQuery())
            false
        }
    }

    fun setQuery(query: String) {
        searchEditText.setText(query)
    }

    interface SearchBarListener {
        fun onSearchBarFocusChanged(hasFocus: Boolean)
        fun onSearchBarEnter(query: String)
        fun onSearchBarTextChange(query: String)
    }
}