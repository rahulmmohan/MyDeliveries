package com.app.mydeliveries.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.app.mydeliveries.datasource.model.Delivery
import com.app.mydeliveries.datasource.paging.DeliveryListDataSourceFactory

class DeliveryViewModel : ViewModel() {
    var deliveryList: LiveData<PagedList<Delivery>>

    init {
        val deliveryListDataSourceFactory = DeliveryListDataSourceFactory()
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20).build()

        deliveryList = LivePagedListBuilder(deliveryListDataSourceFactory, pagedListConfig).build()
    }
}