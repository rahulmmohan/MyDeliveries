package com.app.mydeliveries.datasource.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Deliveries")
class Delivery : Serializable {
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    var id: Int? = null

    @SerializedName("description")
    @ColumnInfo(name = "description")
    var description: String? = null

    @SerializedName("imageUrl")
    @ColumnInfo(name = "imageUrl")
    var imageUrl: String? = null

    @SerializedName("location")
    @Embedded
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