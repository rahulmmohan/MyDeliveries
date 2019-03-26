package com.app.mydeliveries.datasource.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Delivery : Serializable {
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("imageUrl")
    var imageUrl: String? = null
    @SerializedName("location")
    var location: Location? = null

    fun getDeliveryDetail(): String {
        var deliverAt = ""
        location?.let {
            deliverAt = " at ${it.address}"
        }
        description?.let {
            return description + deliverAt
        }
        return ""
    }
}