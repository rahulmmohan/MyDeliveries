package com.app.mydeliveries.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mydeliveries.R
import com.app.mydeliveries.datasource.model.Delivery
import kotlinx.android.synthetic.main.delivery_list_item.view.*

class DeliveryItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private var data: Delivery? = null

    fun bind(data: Delivery?) {
        this.data = data
        data?.let {
            view.descriptionTextView.text = it.description + " "+ it.id ?: ""
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