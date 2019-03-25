package com.app.mydeliveries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mydeliveries.ui.DeliveryAdapter
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
        deliveryViewModel.deliveryList.observe(this, Observer {
            deliveryAdapter.submitList(it)
        })
    }

    private fun setRecyclerView() {
        val llm = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        myDeliveriesListView.layoutManager = llm
        deliveryAdapter = DeliveryAdapter()
        myDeliveriesListView.adapter = deliveryAdapter
    }
}
