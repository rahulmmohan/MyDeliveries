package com.app.mydeliveries.utlis

import android.content.Context
import androidx.core.content.ContextCompat.getSystemService
import android.net.ConnectivityManager


fun isNetwokAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}