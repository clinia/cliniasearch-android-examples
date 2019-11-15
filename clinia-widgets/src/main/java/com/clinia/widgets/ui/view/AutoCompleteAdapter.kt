package com.clinia.widgets.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clinia.widgets.R
import com.clinia.widgets.data.QuerySuggestion


class AutoCompleteAdapter(
    private val data: MutableList<QuerySuggestion>, val onClick: (QuerySuggestion) -> Unit
) :
    RecyclerView.Adapter<AutoCompleteAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.item_autocomplete,
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.setItem(data[i])
    }

    override fun getItemCount() = data.size

    fun setAutoCompleteItems(items: List<QuerySuggestion>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){

        private val textView: TextView = itemView.findViewById(R.id.autoCompleteItem)
        private var suggestion: QuerySuggestion? = null

        fun setItem(item: QuerySuggestion) {
            suggestion = item
            textView.text = item.suggestion
            itemView.setOnClickListener {
                onClick(item)
            }
        }
    }

}