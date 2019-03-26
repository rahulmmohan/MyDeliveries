package com.app.mydeliveries.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.app.mydeliveries.datasource.localDb.AppDatabase
import com.app.mydeliveries.datasource.model.Delivery
import com.app.mydeliveries.datasource.network.ApiClient
import com.app.mydeliveries.datasource.paging.DataRequestState
import com.app.mydeliveries.datasource.paging.DeliveryListDataSourceFactory
import com.app.mydeliveries.utlis.isNetwokAvailable

class DeliveryViewModel(@NonNull application: Application) : AndroidViewModel(application) {
    var deliveryList: LiveData<PagedList<Delivery>>
    var dataRequestState: LiveData<DataRequestState>

    init {
        val deliveryListDataSourceFactory = DeliveryListDataSourceFactory(
            ApiClient.getInstance(),
            AppDatabase.getInstance(application.applicationContext),
            isNetwokAvailable(application.applicationContext)
        )
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .build()

        deliveryList = LivePagedListBuilder(deliveryListDataSourceFactory, pagedListConfig).build()
        dataRequestState = Transformations.switchMap(deliveryListDataSourceFactory.dataSource) { dataSource ->
            dataSource.dataRequestState
        }

    }
}