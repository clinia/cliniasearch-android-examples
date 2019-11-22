package com.clinia.widgets.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

/**
 * The ResultsList widget is intended for use with the ResultsAdapter and CliniaViewModel classes.
 *
 * It displays the HealthFacility results using Material Design cards and displays their name,
 * address,
 *
 * @constructor
 * ResultsList inherits the base constructors from the RecyclerViewClass
 *
 * @param context
 * @param attrs
 * @param defStyleAttr
 */
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

    /**
     * Implement this interface to set the method called when the user scrolls to the bottom of
     * the results list.
     *
     */
    interface OnLoadMoreListener {
        fun onLoadMore()
    }

    fun getResult(id: String) = (adapter as ResultAdapter).getIndex(id)
}