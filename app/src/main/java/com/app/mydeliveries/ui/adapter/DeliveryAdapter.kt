package com.app.mydeliveries.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.mydeliveries.datasource.model.Delivery
import com.app.mydeliveries.datasource.paging.DataRequestState

class DeliveryAdapter : PagedListAdapter<Delivery, RecyclerView.ViewHolder>(diffCallback) {

    private var dataRequestState: DataRequestState? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            DELIVERY_ITEM -> run {
                return DeliveryItemViewHolder.create(parent)
            }
            LOADING_ITEM -> run {
                return DataRequestStateItemViewHolder.create(parent)
            }
            else -> run { throw IllegalArgumentException("unknown view type") }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            DELIVERY_ITEM -> (holder as DeliveryItemViewHolder).bind(getItem(position))
            LOADING_ITEM -> (holder as DataRequestStateItemViewHolder).bind(dataRequestState)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoadingVisible() && position == itemCount - 1) {
            LOADING_ITEM
        } else {
            DELIVERY_ITEM
        }
    }

    /**
     * handling pagination loading animation by checking previous datarequest and new datarequest.
     */
    fun setDataRequestState(newDataRequestState: DataRequestState) {
        val previousState = this.dataRequestState
        val previousLoadingVisible = isLoadingVisible()
        this.dataRequestState = newDataRequestState
        val newLoadingVisible = isLoadingVisible()
        if (previousLoadingVisible != newLoadingVisible) {
            if (previousLoadingVisible) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        } else if (newLoadingVisible && previousState !== newDataRequestState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    private fun isLoadingVisible(): Boolean {
        return dataRequestState != null && dataRequestState !== DataRequestState.LOADED
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Delivery>() {
            override fun areItemsTheSame(oldItem: Delivery, newItem: Delivery): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Delivery, newItem: Delivery): Boolean = oldItem == newItem
        }
        const val DELIVERY_ITEM = 0
        const val LOADING_ITEM = 1
    }
}