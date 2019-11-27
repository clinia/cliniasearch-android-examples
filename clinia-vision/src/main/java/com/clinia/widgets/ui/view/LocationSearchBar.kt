package com.clinia.widgets.ui.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import com.clinia.widgets.R
import kotlinx.android.synthetic.main.view_location_search.view.*

/**
 * The LocationSearchBar is a widget intended to be used with the CliniaViewModel
 * to get a location input from the user.
 *
 * @constructor
 * The LocationSearchBar inherits the base constructors from the LinearLayout Class
 *
 * @param context
 * @param attrs
 * @param defStyleAttr
 */
class LocationSearchBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private fun getLocation() =
        if (locationEditText?.text.isNullOrEmpty()) "" else locationEditText?.text.toString()

    lateinit var listener: LocationSearchBarListener

    init {
        inflate(context, R.layout.view_location_search, this)
        locationInputLayout.isEndIconVisible = false
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
            override fun afterTextChanged(p0: Editable?) {
                locationInputLayout.isEndIconVisible = !locationEditText.text.isNullOrEmpty()
            }
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
        locationInputLayout.setEndIconOnClickListener {
            setLocation("")
            listener.onLocationSearchBarCleared()
            locationInputLayout.isEndIconVisible = false
        }
    }

    /**
     * Sets the text displayed in the search bar
     *
     * @param location Text to be displayed
     */
    fun setLocation(location: String) {
        locationEditText.setText(location)
    }

    /**
     * Implement this interface to access various callbacks from the search bar for user interaction
     *
     * onLocationSearchBarFocusChanged(hasFocus: Boolean) is called every time the search bar gets or loses focus.
     * onLocationSearchBarEnter(location: String) is called when the user presses the enter key.
     * onLocationSearchBarTextChange(location: String) is called every time the user modifies the text.
     * onLocationSearchBarCleared() is called when the user presses the clear button.
     *
     */
    interface LocationSearchBarListener {
        fun onLocationSearchBarFocusChanged(hasFocus: Boolean)
        fun onLocationSearchBarEnter(location: String)
        fun onLocationSearchBarTextChange(location: String)
        fun onLocationSearchBarCleared()
    }
}