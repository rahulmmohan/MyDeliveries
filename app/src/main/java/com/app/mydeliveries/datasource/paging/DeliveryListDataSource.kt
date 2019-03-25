package com.app.mydeliveries.datasource.paging

import androidx.paging.ItemKeyedDataSource
import com.app.mydeliveries.datasource.model.Delivery
import com.app.mydeliveries.datasource.network.ApiClient
import com.app.mydeliveries.datasource.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeliveryListDataSource : ItemKeyedDataSource<Int, Delivery>() {
    var apiService: ApiService = ApiClient.getInstance().apiService

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Delivery>) {
        apiService.getAllDeliveries(0, params.requestedLoadSize).enqueue(object : Callback<List<Delivery>> {
            override fun onFailure(call: Call<List<Delivery>>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<List<Delivery>>?, response: Response<List<Delivery>>?) {
                if (response!!.isSuccessful && response.code() == 200) {
                    callback.onResult(response.body())
                }
            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Delivery>) {
        apiService.getAllDeliveries(params.key, params.requestedLoadSize).enqueue(object : Callback<List<Delivery>> {
            override fun onFailure(call: Call<List<Delivery>>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<List<Delivery>>?, response: Response<List<Delivery>>?) {
                if (response!!.isSuccessful && response.code() == 200) {
                    callback.onResult(response.body())
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