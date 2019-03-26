package com.app.mydeliveries.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mydeliveries.R
import com.app.mydeliveries.datasource.model.Delivery
import com.app.mydeliveries.ui.activity.DeliveryDetailActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.delivery_list_item.view.*

class DeliveryItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private var data: Delivery? = null
    /**
     * Binding data to view
     */
    fun bind(data: Delivery?) {
        this.data = data
        data?.let {

            view.descriptionTextView.text = it.description
            view.locationTextView.text = it.location?.address ?: "NA"
            Glide
                .with(view.context)
                .load(it.imageUrl)
                .placeholder(R.drawable.ic_action_person)
                .into(view.receiverImageView)
        }
        view.setOnClickListener {
            val intent = Intent(view.context, DeliveryDetailActivity::class.java).apply {
                putExtra("item", data)
            }
            view.context.startActivity(intent)
        }
    }

    companion object {
        fun create(parent: ViewGroup): DeliveryItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.delivery_list_item, parent, false)
            return DeliveryItemViewHolder(view)
        }
    }
}