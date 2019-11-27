package com.clinia.widgets.ui.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import com.clinia.widgets.R
import kotlinx.android.synthetic.main.view_search.view.*

/**
 * The SearchBar is a widget intended to be used with the CliniaViewModel
 * to get a query input from the user.
 *
 * @constructor
 * The SearchBar inherits the base constructors from the LinearLayout Class
 *
 * @param context
 * @param attrs
 * @param defStyleAttr
 */
class SearchBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private fun getQuery() =
        if (searchEditText?.text.isNullOrEmpty()) "" else searchEditText?.text.toString()

    lateinit var listener: SearchBarListener

    init {
        inflate(context, R.layout.view_search, this)
        searchInputLayout.isEndIconVisible = false
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
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                searchInputLayout.isEndIconVisible = !searchEditText.text.isNullOrEmpty()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                listener.onSearchBarTextChange(getQuery())
            }
        })
        searchEditText.setOnEditorActionListener { _, p1, _ ->
            if (p1 == EditorInfo.IME_ACTION_SEARCH)
                listener.onSearchBarEnter(getQuery())
            false
        }
        searchInputLayout.setEndIconOnClickListener {
            setQuery("")
            listener.onSearchBarCleared()
            searchInputLayout.isEndIconVisible = false
        }
    }

    /**
     * Sets the text displayed in the search bar
     *
     * @param query Text to be displayed
     */
    fun setQuery(query: String) {
        searchEditText.setText(query)
    }

    /**
     * Implement this interface to access various callbacks from the search bar for user interaction
     *
     * onSearchBarFocusChanged(hasFocus: Boolean) is called every time the search bar gets or loses focus.
     * onSearchBarEnter(query: String) is called when the user presses the enter key.
     * onSearchBarTextChange(query: String) is called every time the user modifies the text.
     * onSearchBarCleared() is called when the user presses the clear button.
     *
     */
    interface SearchBarListener {
        fun onSearchBarFocusChanged(hasFocus: Boolean)
        fun onSearchBarEnter(query: String)
        fun onSearchBarTextChange(query: String)
        fun onSearchBarCleared()
    }
}