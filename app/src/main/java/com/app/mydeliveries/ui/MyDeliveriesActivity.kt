package com.app.mydeliveries.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mydeliveries.R
import com.app.mydeliveries.viewmodel.DeliveryViewModel
import kotlinx.android.synthetic.main.activity_my_deliveries.*


class MyDeliveriesActivity : AppCompatActivity() {
    lateinit var deliveryViewModel: DeliveryViewModel
    private lateinit var deliveryAdapter: DeliveryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_deliveries)
        deliveryViewModel = ViewModelProviders.of(this).get(DeliveryViewModel::class.java)
        setRecyclerView()
        deliveryViewModel.dataRequestState.observe(this, Observer { dataRequestState ->
            dataRequestState.let {
                deliveryAdapter.setDataRequestState(it)
            }
        })
        deliveryViewModel.deliveryList.observe(this, Observer {
            deliveryAdapter.submitList(it)
            progressBar.visibility = View.GONE
        })
    }

    private fun setRecyclerView() {
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        myDeliveriesListView.layoutManager = layoutManager
        myDeliveriesListView.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
        deliveryAdapter = DeliveryAdapter()
        myDeliveriesListView.adapter = deliveryAdapter
    }
}
