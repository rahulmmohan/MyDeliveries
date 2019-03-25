package com.app.mydeliveries.ui

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.mydeliveries.datasource.model.Delivery

class DeliveryAdapter : PagedListAdapter<Delivery, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DeliveryItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DeliveryItemViewHolder).bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Delivery>() {
            override fun areItemsTheSame(oldItem: Delivery, newItem: Delivery): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Delivery, newItem: Delivery): Boolean = oldItem == newItem
        }
    }
}