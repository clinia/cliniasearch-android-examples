package com.clinia.widgets.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView


class ResultsList @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    lateinit var listener: OnLoadMoreListener

    init {
        this.addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    listener.onLoadMore()
                }
            }
        })
    }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }

    fun getResult(id: String) = (adapter as ResultAdapter).getIndex(id)
}