package com.app.mydeliveries.datasource.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.app.mydeliveries.datasource.model.Delivery
import com.app.mydeliveries.datasource.network.ApiClient
import com.app.mydeliveries.datasource.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeliveryListDataSource : ItemKeyedDataSource<Int, Delivery>() {
    var apiService: ApiService = ApiClient.getInstance().apiService
    var dataRequestState: MutableLiveData<DataRequestState> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Delivery>) {
        dataRequestState.postValue(DataRequestState.LOADING)
        apiService.getAllDeliveries(0, params.requestedLoadSize).enqueue(object : Callback<List<Delivery>> {
            override fun onFailure(call: Call<List<Delivery>>?, t: Throwable?) {
                dataRequestState.postValue(DataRequestState(DataRequestState.Status.FAILED))
            }

            override fun onResponse(call: Call<List<Delivery>>?, response: Response<List<Delivery>>?) {
                if (response!!.isSuccessful && response.code() == 200) {
                    callback.onResult(response.body())
                    dataRequestState.postValue(DataRequestState.LOADED)
                } else {
                    dataRequestState.postValue(DataRequestState(DataRequestState.Status.FAILED))
                }
            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Delivery>) {
        dataRequestState.postValue(DataRequestState.LOADING)
        apiService.getAllDeliveries(params.key, params.requestedLoadSize).enqueue(object : Callback<List<Delivery>> {
            override fun onFailure(call: Call<List<Delivery>>?, t: Throwable?) {
                dataRequestState.postValue(DataRequestState(DataRequestState.Status.FAILED))
            }

            override fun onResponse(call: Call<List<Delivery>>?, response: Response<List<Delivery>>?) {
                if (response!!.isSuccessful && response.code() == 200) {
                    callback.onResult(response.body())
                    dataRequestState.postValue(DataRequestState.LOADED)
                } else {
                    dataRequestState.postValue(DataRequestState(DataRequestState.Status.FAILED))
                }
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Delivery>) {
    }

    override fun getKey(item: Delivery): Int {
        item.id?.let {
            return it.plus(1)
        }
        return 0
    }
}