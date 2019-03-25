package com.app.mydeliveries.datasource.paging

import androidx.paging.DataSource
import com.app.mydeliveries.datasource.model.Delivery

class DeliveryListDataSourceFactory : DataSource.Factory<Int, Delivery>() {
    private lateinit var deliveryListDataSource: DeliveryListDataSource
    override fun create(): DataSource<Int, Delivery> {
        deliveryListDataSource = DeliveryListDataSource()
        return deliveryListDataSource
    }
}