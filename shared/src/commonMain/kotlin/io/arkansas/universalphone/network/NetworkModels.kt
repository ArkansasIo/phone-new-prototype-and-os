package io.arkansas.universalphone.network

import kotlinx.serialization.Serializable

@Serializable
enum class NetworkType { NONE, WIFI, CELLULAR, ETHERNET, BLUETOOTH, VPN, LOOPBACK }

@Serializable
data class NetworkNode(
    val id: String,
    val parentId: String? = null,
    val name: String,
    val type: NetworkType,
    val address: String? = null,
    val online: Boolean = false,
    val latencyMs: Long? = null,
    val children: List<String> = emptyList()
)

@Serializable
data class NetworkLink(
    val from: String,
    val to: String,
    val bandwidthMbps: Long? = null,
    val latencyMs: Long? = null,
    val active: Boolean = true
)

@Serializable
data class NetworkSnapshot(
    val timestampEpochMs: Long,
    val nodes: List<NetworkNode>,
    val links: List<NetworkLink>
)

@Serializable
data class NetworkInterfaceInfo(
    val id: String,
    val displayName: String,
    val type: NetworkType,
    val macAddress: String? = null,
    val ipv4Addresses: List<String> = emptyList(),
    val ipv6Addresses: List<String> = emptyList(),
    val mtu: Int = 1500,
    val connected: Boolean = false
)
