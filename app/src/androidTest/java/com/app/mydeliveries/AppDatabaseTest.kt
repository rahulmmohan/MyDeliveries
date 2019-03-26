package com.app.mydeliveries

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.app.mydeliveries.datasource.localDb.AppDatabase
import com.app.mydeliveries.datasource.localDb.DeliveriesDao
import com.app.mydeliveries.datasource.model.Delivery
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    companion object {
        private var deliveriesDao: DeliveriesDao? = null
        @BeforeClass
        fun initDb() {
            deliveriesDao = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                AppDatabase::class.java
            ).build().deliveryDao()
        }
    }


    @Test
    fun testA_should_Insert_Delivery_Item() {
        val delivery = Delivery()
        delivery.id = Random().nextInt()
        delivery.description = "description"

        deliveriesDao?.let {
            val rowCount = it.insertAll(items = arrayListOf(delivery))
            Assert.assertEquals(delivery.id, rowCount[0].toInt())
        }
    }

    @Test
    fun testB_should_Return_Delivery_Items() {
        deliveriesDao?.let {
            val rows = it.getDeliveryItems(0, 1)
            Assert.assertEquals(1, rows.size)
        }
    }
}