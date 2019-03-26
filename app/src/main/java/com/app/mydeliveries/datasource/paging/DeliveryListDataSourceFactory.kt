package com.app.mydeliveries.datasource.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.app.mydeliveries.datasource.localDb.AppDatabase
import com.app.mydeliveries.datasource.model.Delivery
import com.app.mydeliveries.datasource.network.ApiClient

class DeliveryListDataSourceFactory(
    private val apiClient: ApiClient,
    private val appDatabase: AppDatabase,
    private val isNetWorkAvailable: Boolean
) : DataSource.Factory<Int, Delivery>() {
    var dataSource: MutableLiveData<DeliveryListDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, Delivery> {
        val deliveryListDataSource = DeliveryListDataSource(apiClient, appDatabase, isNetWorkAvailable)
        dataSource.postValue(deliveryListDataSource)
        return deliveryListDataSource
    }
}