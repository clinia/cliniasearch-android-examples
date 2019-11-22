package com.clinia.widgets.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clinia.widgets.R
import com.clinia.widgets.data.PlaceSuggestion
import com.clinia.widgets.data.QuerySuggestion

/**
 * The AutoCompleteAdapter is intended for use with the AutoComplete widget.
 * It is a custom class extending a RecyclerView  Adapter with a generic ViewHolder accepting both
 * QuerySuggestion and PlaceSuggestion objects.
 *
 * @property data       MutableList<Any> containing the items to be displayed in the adapter
 * @property onClick    (Any) -> Unit Method to be called when an item is clicked.
 */
class AutoCompleteAdapter(
    private val data: MutableList<Any>, val onClick: (Any) -> Unit
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BaseViewHolder<*> {
        return when (i) {
            TYPE_QUERY -> QueryViewHolder(
                LayoutInflater.from(viewGroup.context).inflate(
                    R.layout.item_autocomplete,
                    viewGroup,
                    false
                )
            )
            TYPE_PLACE -> PlaceViewHolder(
                LayoutInflater.from(viewGroup.context).inflate(
                    R.layout.item_autocomplete,
                    viewGroup,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid data type at position $i")
        }

    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = data[position]
        when (holder) {
            is QueryViewHolder -> holder.bind(item as QuerySuggestion)
            is PlaceViewHolder -> holder.bind(item as PlaceSuggestion)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is QuerySuggestion -> TYPE_QUERY
            is PlaceSuggestion -> TYPE_PLACE
            else -> throw IllegalArgumentException("Invalid data type at position $position")
        }
    }

    override fun getItemCount() = data.size

    fun setAutoCompleteItems(items: List<Any>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    inner class QueryViewHolder(itemView: View) : BaseViewHolder<QuerySuggestion>(itemView) {

        private val textView: TextView = itemView.findViewById(R.id.autoCompleteItem)
        private var suggestion: QuerySuggestion? = null

        override fun bind(item: QuerySuggestion) {
            suggestion = item
            textView.text = item.suggestion
            itemView.setOnClickListener {
                onClick(item)
            }
        }
    }

    inner class PlaceViewHolder(itemView: View) : BaseViewHolder<PlaceSuggestion>(itemView) {

        private val textView: TextView = itemView.findViewById(R.id.autoCompleteItem)
        private var suggestion: PlaceSuggestion? = null

        override fun bind(item: PlaceSuggestion) {
            suggestion = item
            textView.text = item.formattedAddress
            itemView.setOnClickListener {
                onClick(item)
            }
        }
    }

    companion object {
        private const val TYPE_QUERY = 0
        private const val TYPE_PLACE = 1
    }
}

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}