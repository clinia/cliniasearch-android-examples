package com.clinia.widgets.ui.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.clinia.widgets.R
import com.clinia.widgets.data.QuerySuggestion
import kotlinx.android.synthetic.main.view_autocomplete.view.*

class AutoComplete @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    lateinit var listener: AutoCompleteListener
    private var adapter: AutoCompleteAdapter

    init {
        inflate(context, R.layout.view_autocomplete, this)

        context.obtainStyledAttributes(attrs, R.styleable.AutoComplete).apply {
            try {
                autoCompleteTitle.text = getString(R.styleable.AutoComplete_title)
                autoCompleteTitle.setTextColor(
                    getColor(
                        R.styleable.AutoComplete_titleTextColor,
                        Color.BLACK
                    )
                )
                autoCompleteTitle.textSize = getDimension(
                    R.styleable.AutoComplete_titleTextSize,
                    resources.getDimension(R.dimen.font_normal)
                )
            } finally {
                recycle()
            }
        }
        adapter = AutoCompleteAdapter(mutableListOf()) { query ->
            listener.onAutoCompleteItemClicked(query)
            this.visibility = View.GONE
        }
        autoCompleteList.adapter = adapter
        autoCompleteList.layoutManager = LinearLayoutManager(context)
    }

    fun setAutoCompleteItems(items: List<QuerySuggestion>) {
        adapter.setAutoCompleteItems(items)
    }

    interface AutoCompleteListener {
        fun onAutoCompleteItemClicked(querySuggestion: QuerySuggestion)
    }
}