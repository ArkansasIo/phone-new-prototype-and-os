package io.arkansas.universalphone.virtualnet

import kotlinx.serialization.Serializable

@Serializable
enum class VirtualNetworkType { SANDBOX, NAT, BRIDGE, HOST_ONLY, OVERLAY, VPN, LOOPBACK }

@Serializable
data class VirtualNetwork(
    val id: String,
    val name: String,
    val type: VirtualNetworkType = VirtualNetworkType.SANDBOX,
    val cidr: String = "10.0.0.0/24",
    val gateway: String? = "10.0.0.1",
    val dns: List<String> = listOf("1.1.1.1", "8.8.8.8"),
    val dhcpEnabled: Boolean = true,
    val isolated: Boolean = true,
    val enabled: Boolean = true
)

@Serializable
data class VirtualNic(
    val id: String,
    val networkId: String,
    val name: String,
    val macAddress: String,
    val ipv4Address: String? = null,
    val connected: Boolean = false
)

@Serializable
data class VirtualRoute(
    val networkId: String,
    val destinationCidr: String,
    val nextHop: String?,
    val metric: Int = 100
)

@Serializable
data class VirtualNode(
    val id: String,
    val name: String,
    val networkId: String,
    val nics: List<String> = emptyList(),
    val online: Boolean = false
)

@Serializable
data class VirtualNetworkSnapshot(
    val timestampEpochMs: Long,
    val networks: List<VirtualNetwork>,
    val nics: List<VirtualNic>,
    val nodes: List<VirtualNode>,
    val routes: List<VirtualRoute>
)
