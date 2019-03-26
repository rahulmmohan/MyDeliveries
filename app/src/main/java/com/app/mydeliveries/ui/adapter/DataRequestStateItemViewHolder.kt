package com.app.mydeliveries.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mydeliveries.R
import com.app.mydeliveries.datasource.paging.DataRequestState
import kotlinx.android.synthetic.main.data_request_state_item.view.*

class DataRequestStateItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(networkState: DataRequestState?) {
        if (networkState != null && networkState.status === DataRequestState.Status.RUNNING) {
            view.progress_bar.visibility = View.VISIBLE
        } else {
            view.progress_bar.visibility = View.GONE
        }
    }

    companion object {
        fun create(parent: ViewGroup): DataRequestStateItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.data_request_state_item, parent, false)
            return DataRequestStateItemViewHolder(view)
        }
    }
}