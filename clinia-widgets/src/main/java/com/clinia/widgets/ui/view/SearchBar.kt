package com.clinia.widgets.ui.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import com.clinia.widgets.R
import com.clinia.widgets.ui.onRightDrawableClicked
import kotlinx.android.synthetic.main.view_search.view.*

class SearchBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private fun getQuery() =
        if (searchEditText?.text.isNullOrEmpty()) "" else searchEditText?.text.toString()

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
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
//                searchEditText.setCompoundDrawablesWithIntrinsicBounds(
//                    0, 0,
//                    if (searchEditText.text.isNullOrEmpty()) 0 else R.drawable.ic_clear,
//                    0
//                )
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
        searchEditText.onRightDrawableClicked {
            setQuery("")
            listener.onSearchBarCleared()
        }
    }

    fun setQuery(query: String) {
        searchEditText.setText(query)
    }

    interface SearchBarListener {
        fun onSearchBarFocusChanged(hasFocus: Boolean)
        fun onSearchBarEnter(query: String)
        fun onSearchBarTextChange(query: String)
        fun onSearchBarCleared()
    }
}