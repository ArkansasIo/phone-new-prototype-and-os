package io.arkansas.universalphone.android.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import io.arkansas.universalphone.network.NetworkAdapter
import io.arkansas.universalphone.network.NetworkInterfaceInfo
import io.arkansas.universalphone.network.NetworkNode
import io.arkansas.universalphone.network.NetworkSnapshot
import io.arkansas.universalphone.network.NetworkType

class AndroidNetworkAdapter(context: Context) : NetworkAdapter {
    private val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override suspend fun interfaces(): List<NetworkInterfaceInfo> {
        val active = connectivity.activeNetwork ?: return emptyList()
        val caps = connectivity.getNetworkCapabilities(active) ?: return emptyList()
        val type = when {
            caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> NetworkType.WIFI
            caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> NetworkType.CELLULAR
            caps.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> NetworkType.ETHERNET
            caps.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> NetworkType.BLUETOOTH
            else -> NetworkType.NONE
        }
        return listOf(NetworkInterfaceInfo("active", type.name, type, connected = true))
    }

    override suspend fun snapshot(): NetworkSnapshot {
        val list = interfaces()
        val nodes = list.map { info ->
            NetworkNode("interface:${info.id}", "phone", info.displayName, info.type, online = info.connected)
        }
        val root = NetworkNode("phone", null, "Phone", NetworkType.LOOPBACK, online = true)
        return NetworkSnapshot(System.currentTimeMillis(), listOf(root) + nodes, emptyList())
    }

    override suspend fun connect(interfaceId: String): Result<Unit> =
        Result.failure(UnsupportedOperationException("Android connectivity is controlled by system APIs"))

    override suspend fun disconnect(interfaceId: String): Result<Unit> =
        Result.failure(UnsupportedOperationException("Android connectivity is controlled by system APIs"))
}
