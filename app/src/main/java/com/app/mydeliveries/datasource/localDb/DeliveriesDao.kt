package com.app.mydeliveries.datasource.localDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.mydeliveries.datasource.model.Delivery

@Dao
interface DeliveriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<Delivery>): List<Long>

    @Query("SELECT * FROM Deliveries LIMIT :limit OFFSET :offset")
    fun getDeliveryItems(offset: Int, limit: Int): List<Delivery>

    @Query("SELECT * FROM Deliveries ")
    fun getAll(): List<Delivery>
}