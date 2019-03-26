package com.app.mydeliveries.datasource.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.app.mydeliveries.datasource.localDb.AppDatabase
import com.app.mydeliveries.datasource.model.Delivery
import com.app.mydeliveries.datasource.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeliveryListDataSource(
    private val apiClient: ApiClient,
    private val appDatabase: AppDatabase,
    private val isNetWorkAvailable: Boolean
) : ItemKeyedDataSource<Int, Delivery>() {

    var dataRequestState: MutableLiveData<DataRequestState> = MutableLiveData()


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Delivery>) {
        if (isNetWorkAvailable) {
            requestDataFromServer(0, params.requestedLoadSize, callback)
        } else {
            requestDataFromDB(0, params.requestedLoadSize, callback)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Delivery>) {
        if (isNetWorkAvailable) {
            requestDataFromServer(params.key, params.requestedLoadSize, callback)
        } else {
            requestDataFromDB(params.key, params.requestedLoadSize, callback)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Delivery>) {}
    /**
     * return next offset to fetch data based on the item id plus 1.
     */
    override fun getKey(item: Delivery): Int {
        item.id?.let {
            return it.plus(1)
        }
        return 0
    }

    /**
     * fetch data from server using offset and limit and return data using callback.
     */
    private fun requestDataFromServer(
        offset: Int, limit: Int,
        callback: LoadCallback<Delivery>
    ) {
        dataRequestState.postValue(DataRequestState.LOADING)
        apiClient.apiService.getAllDeliveries(offset, limit)
            .enqueue(object : Callback<List<Delivery>> {
                override fun onFailure(call: Call<List<Delivery>>?, t: Throwable?) {
                    requestDataFromDB(offset,limit,callback)
                }

                override fun onResponse(call: Call<List<Delivery>>?, response: Response<List<Delivery>>?) {
                    if (response!!.isSuccessful && response.code() == 200) {
                        callback.onResult(response.body())
                        appDatabase.deliveryDao().insertAll(response.body())
                        dataRequestState.postValue(DataRequestState.LOADED)
                    } else {
                        dataRequestState.postValue(DataRequestState(DataRequestState.Status.FAILED))
                    }
                }

            })
    }

    /**
     * fetch data from local db if no network available.
     */
    private fun requestDataFromDB(
        offset: Int, limit: Int,
        callback: LoadCallback<Delivery>
    ) {
        dataRequestState.postValue(DataRequestState.LOADING)
        val deliveryItems = appDatabase.deliveryDao().getDeliveryItems(offset, limit)
        callback.onResult(deliveryItems)
        dataRequestState.postValue(DataRequestState.LOADED)
    }
}