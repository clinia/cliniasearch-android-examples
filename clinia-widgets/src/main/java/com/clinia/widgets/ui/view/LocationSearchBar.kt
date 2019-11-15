package com.clinia.widgets.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import com.clinia.widgets.R
import kotlinx.android.synthetic.main.view_location_search.view.*

//class SearchBar @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
//) : LinearLayout(context, attrs, defStyleAttr) {

class LocationSearchBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private fun getLocation() = if (locationEditText?.text.isNullOrEmpty()) "" else locationEditText?.text.toString()

    lateinit var listener: LocationSearchBarListener

    init {
        inflate(context, R.layout.view_location_search, this)

        context.obtainStyledAttributes(attrs, R.styleable.LocationSearchBar).apply {
            try {
                locationEditText.hint = getString(R.styleable.LocationSearchBar_hintLocation)
            } finally {
                recycle()
            }
        }
        locationEditText.setOnEditorActionListener { _, p1, _ ->
            if (p1 == EditorInfo.IME_ACTION_SEARCH)
                listener.onLocationSearchBarEnter(getLocation())
            false
        }
    }

    interface LocationSearchBarListener {
        fun onLocationSearchBarEnter(location: String)
        fun onLocationSearchBarTextChange()
    }
}