package com.clinia.widgets.ui.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clinia.widgets.R
import com.clinia.widgets.data.HealthFacility
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.result_card_view.view.*
import java.util.*

class ResultAdapter(private val context: Context, private var data: MutableList<HealthFacility>) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    var onLoadMoreListener: OnLoadMoreListener? = null

    class ViewHolder(val resultCard: MaterialCardView) : RecyclerView.ViewHolder(resultCard)

    fun setData(mutableList: MutableList<HealthFacility>) {
        data = mutableList
        notifyDataSetChanged()
    }

    fun getIndex(records: HealthFacility) = data.indexOf(records)

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
        data[position].distance?.let {
            holder.resultCard.distance.text = Formatter().format(
                context.resources.getString(R.string.record_distance),
                it
            ).toString()
            holder.resultCard.distance.visibility = View.VISIBLE
        }
        data[position].name?.let {
            holder.resultCard.name.text = it
        }
        data[position].address?.let {
            holder.resultCard.address.text =
                context.getString(
                    R.string.record_address,
                    it.streetAddress,
                    it.place
                )
        }

        if (data[position].getFormattedHours() != null) {
            holder.resultCard.openingHours.visibility = View.VISIBLE
            holder.resultCard.openingHours.text = data[position].getFormattedHours()
        } else {
            holder.resultCard.openingHours.visibility = View.INVISIBLE
        }

        holder.resultCard.directionsBtn.visibility =
            if (data[position].phones.isNullOrEmpty()) View.GONE else View.VISIBLE
        holder.resultCard.directionsBtn.setOnClickListener {
            //maps intent using geopoint
            data[position].geoPoint?.let {
                val gmmIntentUri = Uri.parse("google.navigation:q=${it.lat}, ${it.lng}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                context.startActivity(mapIntent)
            }
        }
        holder.resultCard.callBtn.visibility =
            if (data[position].phones.isNullOrEmpty()) View.INVISIBLE else View.VISIBLE
        holder.resultCard.callBtn.setOnClickListener {
            val phoneNumber = data[position].phones?.first()?.number
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount() = data.size

    interface OnLoadMoreListener {
        fun onLoadMore()
    }
}