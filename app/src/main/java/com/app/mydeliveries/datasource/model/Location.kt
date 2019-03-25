package com.app.mydeliveries.datasource.model

import com.google.gson.annotations.SerializedName


class Location {
    @SerializedName("lat")
    var lat: Double? = null
    @SerializedName("lng")
    var lng: Double? = null
    @SerializedName("address")
    var address: String? = null
}