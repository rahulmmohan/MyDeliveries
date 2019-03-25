package com.app.mydeliveries.datasource.model

import com.google.gson.annotations.SerializedName

class Delivery {
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("imageUrl")
    var imageUrl: String? = null
    @SerializedName("location")
    var location: Location? = null
}