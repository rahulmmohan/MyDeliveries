package com.app.mydeliveries.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mydeliveries.R
import com.app.mydeliveries.datasource.paging.DataRequestState
import com.app.mydeliveries.ui.adapter.DeliveryAdapter
import com.app.mydeliveries.viewmodel.DeliveryViewModel
import kotlinx.android.synthetic.main.activity_my_deliveries.*


class MyDeliveriesActivity : AppCompatActivity() {
    private lateinit var deliveryViewModel: DeliveryViewModel
    private lateinit var deliveryAdapter: DeliveryAdapter
    private var isInitialLoading = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_deliveries)
        deliveryViewModel = ViewModelProviders.of(this).get(DeliveryViewModel::class.java)
        setRecyclerView()
        //Observing datarequest changes
        deliveryViewModel.dataRequestState.observe(this, Observer { dataRequestState ->
            dataRequestState.let {
                deliveryAdapter.setDataRequestState(it)
                if (isInitialLoading) {
                    updateUI(it)
                }
            }
        })
        //Observing data fetch
        deliveryViewModel.deliveryList.observe(this, Observer {
            deliveryAdapter.submitList(it)
        })
    }

    /**
     * Handing visibility of progressbar after initial data fetch
     */
    private fun updateUI(it: DataRequestState) {
        if (it.status == DataRequestState.Status.SUCCESS || it.status == DataRequestState.Status.FAILED) {
            isInitialLoading = false
            progressBar.visibility = View.GONE
        }
    }

    /**
     * initializing RecyclerView with layout manager and adapter.
     */
    private fun setRecyclerView() {
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        myDeliveriesListView.layoutManager = layoutManager
        myDeliveriesListView.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
        deliveryAdapter = DeliveryAdapter()
        myDeliveriesListView.adapter = deliveryAdapter
    }
}
