package com.clinia.widgets.ui.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import com.clinia.widgets.R
import com.clinia.widgets.ui.onRightDrawableClicked
import kotlinx.android.synthetic.main.view_location_search.view.*

//class SearchBar @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
//) : LinearLayout(context, attrs, defStyleAttr) {

class LocationSearchBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private fun getLocation() =
        if (locationEditText?.text.isNullOrEmpty()) "" else locationEditText?.text.toString()

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
        locationEditText.setOnFocusChangeListener { _, b ->
            listener.onLocationSearchBarFocusChanged(b)
        }
        locationEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                listener.onLocationSearchBarTextChange(getLocation())
            }
        })
        locationEditText.setOnEditorActionListener { _, p1, _ ->
            if (p1 == EditorInfo.IME_ACTION_SEARCH)
                listener.onLocationSearchBarEnter(getLocation())
            false
        }
        locationEditText.onRightDrawableClicked {
            setLocation("")
            listener.onLocationSearchBarCleared()
        }
    }

    fun setLocation(location: String) {
        locationEditText.setText(location)
    }

    interface LocationSearchBarListener {
        fun onLocationSearchBarFocusChanged(hasFocus: Boolean)
        fun onLocationSearchBarEnter(location: String)
        fun onLocationSearchBarTextChange(location: String)
        fun onLocationSearchBarCleared()
    }
}