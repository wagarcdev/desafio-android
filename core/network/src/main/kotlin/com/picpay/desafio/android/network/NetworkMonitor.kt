package com.picpay.desafio.android.network

import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NetworkMonitor(private val connectivityManager: ConnectivityManager) {

    private var _networkStatus = MutableStateFlow(false)
    val networkStatus = _networkStatus.asStateFlow()

    private var networkCallback: NetworkCallback

    init {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                _networkStatus.update { true }
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                _networkStatus.update { true }
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                _networkStatus.update { false }
            }
        }
        
        connectivityManager
            .requestNetwork(
                networkRequest,
                networkCallback
            )
    }



    fun cleanup() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}