package com.sithuhein.mm.data.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConnection @Inject constructor(context: Context) {

            private val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            fun isConnected(): Boolean {
                when {
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                        val cap: NetworkCapabilities =
                            cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
                        return cap.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    }
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                        val networks = cm.allNetworks
                        for (network in networks) {
                            val nInfo = cm.getNetworkInfo(network)
                            if (nInfo != null && nInfo.isConnected) return true
                        }
                    }
                    else -> {
                        val networks = cm.allNetworkInfo
                        for (nInfo in networks) {
                            if (nInfo != null && nInfo.isConnected) return true
                        }
                    }
                }

                return false
            }

}

