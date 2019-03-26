package com.app.mydeliveries.datasource.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Location : Serializable {
    @SerializedName("lat")
    @ColumnInfo(name = "lat")
    var lat: Double? = null

    @SerializedName("lng")
    @ColumnInfo(name = "lng")
    var lng: Double? = null

    @SerializedName("address")
    @ColumnInfo(name = "address")
    var address: String? = null
}