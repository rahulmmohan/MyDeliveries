package com.app.mydeliveries.datasource.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.app.mydeliveries.datasource.model.Delivery

class DeliveryListDataSourceFactory : DataSource.Factory<Int, Delivery>() {
    var dataSource: MutableLiveData<DeliveryListDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, Delivery> {
        val deliveryListDataSource = DeliveryListDataSource()
        dataSource.postValue(deliveryListDataSource)
        return deliveryListDataSource
    }
}