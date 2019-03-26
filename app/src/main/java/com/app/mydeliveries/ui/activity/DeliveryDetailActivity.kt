package com.app.mydeliveries.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.mydeliveries.R
import com.app.mydeliveries.datasource.model.Delivery
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_delivery_detail.*
import kotlinx.android.synthetic.main.delivery_list_item.*


class DeliveryDetailActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private var deliveryData: Delivery? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_detail)
        setSupportActionBar(toolbar)
        intent.extras?.let {
            deliveryData = it.get("item") as Delivery
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setDeliveryDetails()
    }

    private fun setDeliveryDetails() {
        deliveryData?.let {
            descriptionTextView.text = it.getDeliveryDetail()
            Glide
                .with(this@DeliveryDetailActivity)
                .load(it.imageUrl)
                .into(receiverImageView)
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera
        deliveryData?.let { delivery ->
            delivery.location?.let {
                val location = LatLng(it.lat ?: 0.0, it.lng ?: 0.0)
                mMap.addMarker(MarkerOptions().position(location).title(it.address ?: ""))
                val zoomLevel = 18.0.toFloat()
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel))
            }
        }
    }
}
