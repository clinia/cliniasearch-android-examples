package com.clinia.widgets.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class ResultsList @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    fun getResult(id: String) = (adapter as ResultAdapter).getIndex(id)
}