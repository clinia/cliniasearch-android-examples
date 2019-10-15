package com.clinia.widgets.ui.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clinia.widgets.R
import com.clinia.widgets.data.Records
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.result_card_view.view.*

class ResultAdapter(private val context: Context, private var data: MutableList<Records>) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    class ViewHolder(val resultCard: MaterialCardView) : RecyclerView.ViewHolder(resultCard)

    fun setData(mutableList: MutableList<Records>) {
        data = mutableList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        // create a new view
        val card = LayoutInflater.from(parent.context)
            .inflate(R.layout.result_card_view, parent, false) as MaterialCardView
        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(card)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        setAttributes(holder)

        holder.resultCard.type.text = data[position].type
        holder.resultCard.distance.text = data[position].distance.toString()
        holder.resultCard.name.text = data[position].name
        holder.resultCard.address.text =
            context.getString(
                R.string.record_address,
                data[position].address?.streetAddress,
                data[position].address?.place
            )
        holder.resultCard.openingHours.text =
            "Open now until ${data[position].openingHours?.monday}"
        holder.resultCard.directionsBtn.setOnClickListener {
            //maps intent using geopoint
        }
        holder.resultCard.callBtn.setOnClickListener {
            //phone intent using phone number
        }
    }

    override fun getItemCount() = data.size

    private fun setAttributes(holder: ViewHolder, attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.ResultsList).apply {
            try {
                holder.itemView.minimumHeight = getInt(R.styleable.ResultsList_minCardHeight, 0)

                holder.resultCard.distance.visibility =
                    if (getBoolean(R.styleable.ResultsList_showDistance, true))
                        View.VISIBLE
                    else
                        View.GONE
                holder.resultCard.directionsBtn.visibility =
                    if (getBoolean(R.styleable.ResultsList_showDirectionsButton, true))
                        View.VISIBLE
                    else
                        View.GONE
                holder.resultCard.callBtn.visibility =
                    if (getBoolean(R.styleable.ResultsList_showCallButton, true))
                        View.VISIBLE
                    else
                        View.GONE
                holder.resultCard.openingHours.visibility =
                    if (getBoolean(R.styleable.ResultsList_showOpeningHours, true))
                        View.VISIBLE
                    else
                        View.GONE
                holder.resultCard.address.visibility =
                    if (getBoolean(R.styleable.ResultsList_showAddress, true))
                        View.VISIBLE
                    else
                        View.GONE
                holder.resultCard.name.setTextColor(
                    getColor(R.styleable.ResultsList_titleTextColor, Color.BLACK)
                )
                holder.resultCard.address.setTextColor(
                    getColor(R.styleable.ResultsList_descriptionTextColor, Color.BLACK)
                )
                holder.resultCard.openingHours.setTextColor(
                    getColor(R.styleable.ResultsList_descriptionTextColor, Color.BLACK)
                )
                holder.resultCard.type.setTextColor(
                    getColor(R.styleable.ResultsList_typeTextColor, Color.BLACK)
                )
                holder.resultCard.distance.setTextColor(
                    getColor(R.styleable.ResultsList_distanceTextColor, Color.BLACK)
                )
                holder.resultCard.directionsBtn.setTextColor(
                    getColor(R.styleable.ResultsList_buttonTextColor, Color.BLACK)
                )
                holder.resultCard.callBtn.setTextColor(
                    getColor(R.styleable.ResultsList_buttonTextColor, Color.BLACK)
                )
            } finally {
                recycle()
            }
        }
    }
}